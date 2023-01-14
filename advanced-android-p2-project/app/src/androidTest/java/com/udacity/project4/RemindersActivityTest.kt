package com.udacity.project4

import android.app.Application
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.android.material.internal.ContextUtils.getActivity
import com.udacity.project4.locationreminders.RemindersActivity
import com.udacity.project4.locationreminders.data.DefaultRemindersRepository
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.RemindersRepository
import com.udacity.project4.locationreminders.data.source.local.LocalDB
import com.udacity.project4.locationreminders.data.source.local.LocalDataSource
import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import com.udacity.project4.locationreminders.reminderslist.RemindersListViewModel
import com.udacity.project4.locationreminders.savereminder.SaveReminderViewModel
import com.udacity.project4.util.DataBindingIdlingResource
import com.udacity.project4.util.monitorActivity
import com.udacity.project4.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get


@RunWith(AndroidJUnit4::class)
@LargeTest
//END TO END test to black box test the app
class RemindersActivityTest :
    AutoCloseKoinTest() {// Extended Koin Test - embed autoclose @after method to close Koin after every test

    private val reminderDTO1 = ReminderDTO("reminderDTO1", "b", "c", 1.1, 2.2, "10")

    private lateinit var remindersListViewModel: RemindersListViewModel
    private lateinit var saveReminderViewModel: SaveReminderViewModel
    private lateinit var repository: RemindersRepository
    private lateinit var appContext: Application

    // For Espresso with DataBinding
    private val dataBindingIdlingResource = DataBindingIdlingResource()

    /**
     * As we use Koin as a Service Locator Library to develop our code, we'll also use Koin to test our code.
     * at this step we will initialize Koin related code to be able to use it in out testing.
     */
    @Before
    fun init() {
        stopKoin()//stop the original app koin
        appContext = getApplicationContext()
        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            viewModel {
                RemindersListViewModel(
                    get(), get() as RemindersRepository
                )
            }
            single {
                //This view model is declared singleton to be used across multiple fragments
                SaveReminderViewModel(
                    get(), get() as RemindersRepository
                )
            }
            single<RemindersRepository> { get() as DefaultRemindersRepository }
            single { DefaultRemindersRepository(get()) }

            single<ReminderDataSource> { get() as LocalDataSource }
            single { LocalDataSource(get()) }

            single { LocalDB.createRemindersDao(appContext) }

        }
        //declare a new koin module
        startKoin {
            androidContext(appContext)
            modules(listOf(myModule))
        }
        //Get our real repository
        repository = get()
        remindersListViewModel = get()
        saveReminderViewModel = get()
        //clear the data to start fresh
        runBlocking {
            repository.deleteAllReminders()
        }
    }

    /**
     * Idling resources tell Espresso that the app is idle or busy. This is needed when operations
     * are not scheduled in the main Looper (for example when executed on a different thread).
     */
    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun openRemindersActivity_AddNewReminder() = runBlocking {
        // Set the initial state
        repository.saveReminder(reminderDTO1)
        repository.getReminders()

        // Start the activity && set dataBinding monitor
        val activityScenario = ActivityScenario.launch(RemindersActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        // Espresso Code
        // The saved reminder is displayed
        onView(withText("reminderDTO1")).check(matches(isDisplayed()))

        // press on the fab to add new reminder
        onView(withId(R.id.addReminderFAB))
            .check(matches(isDisplayed()))
            .perform(click())
        // go to saveReminderFragment
        onView(withId(R.id.reminderTitle)).perform(replaceText("newReminder"))
        onView(withId(R.id.reminderDescription)).perform(replaceText("newDesc"))
        onView(withId(R.id.saveReminder)).check(matches(isDisplayed()))
        onView(withId(R.id.selectLocation))
            .check(matches(isDisplayed()))
            .perform(click())

        // go to selectLocationFragment
        onView(withId(R.id.map_fragment))
            .check(matches(isDisplayed()))
            .perform(longClick())

        onView(withId(R.id.save_btn))
            .check(matches(isDisplayed()))
            .perform(click())

        // back to saveReminder fragment with selectedLocation value
        onView(withId(R.id.selectedLocation)).check(matches(isDisplayed()))
        // press save FAB
        onView(withId(R.id.saveReminder))
            .check(matches(isDisplayed()))
            .perform(click())

        // Verify Toast of Confirmation of saving is displayed on screen
        onView(withText(R.string.reminder_saved)).inRoot(
            withDecorView(not(`is`(getActivity(appContext)?.window?.decorView)))
        ).check(matches(isDisplayed()))

        // Verify reminder is displayed on screen in the reminder list fragment.
        onView(withText("newReminder")).check(matches(isDisplayed()))

        // Make sure the activity is closed.
        activityScenario.close()
    }

}
