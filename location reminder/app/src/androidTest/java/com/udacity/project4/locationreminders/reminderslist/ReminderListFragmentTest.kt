package com.udacity.project4.locationreminders.reminderslist

import android.app.Application
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.android.material.R.id.snackbar_text
import com.udacity.project4.MainCoroutineRule
import com.udacity.project4.R
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.RemindersRepository
import com.udacity.project4.locationreminders.data.source.FakeRemindersRepository
import com.udacity.project4.locationreminders.data.source.local.FakeDataSource
import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
//UI Testing
@MediumTest
class ReminderListFragmentTest : AutoCloseKoinTest() {
    //test the navigation of the fragments. (Done)
    //test the displayed data on the UI. (Done)
    //add testing for the error messages. (Done)

    @get:Rule    // For LiveData Testing ==> livedata.getOrAwaitValue()
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule // singleton of TestCoroutineDispatcher
    var mainCoroutineRule = MainCoroutineRule()

    private val reminderDTO1 = ReminderDTO("a", "b", "c", 1.1, 2.2, "10")

    private lateinit var appContext: Application
    private lateinit var repository: FakeRemindersRepository

    @Before
    fun init() {
        stopKoin()//stop the original app koin
        appContext = getApplicationContext()
        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            viewModel {
                RemindersListViewModel(
                    get(),
                    get() as RemindersRepository
                )
            }

            single<RemindersRepository> { get() as FakeRemindersRepository }
            single { FakeRemindersRepository(get()) }

            single<ReminderDataSource> { get() as FakeDataSource }
            single { FakeDataSource(get()) }

            single { mutableListOf<ReminderDTO>() }

        }

        startKoin {
            androidContext(appContext)
            modules(listOf(myModule))
        }

        //Get our fake repository
        repository = get()

        //clear the data to start fresh
        runBlocking {
            repository.deleteAllReminders()
        }
    }


    @Test
    fun noReminders_NoDataDisplayedInUI() = mainCoroutineRule.runBlockingTest {
        // GIVEN - No Reminders in the data source

        // WHEN - list fragment launched to display reminders
        launchFragmentInContainer<ReminderListFragment>(Bundle.EMPTY, R.style.AppTheme)

        // THEN -  NO DATA is Displayed on the screen
        // ProgressBar is not displayed
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        // RecyclerView is Empty
        onView(withId(R.id.reminderssRecyclerView)).check(matches(hasChildCount(0)))
        // NoDataText is displayed
        onView(withId(R.id.noDataTextView)).check(matches(isDisplayed()))
        // FAB is displayed
        onView(withId(R.id.addReminderFAB)).check(matches(isDisplayed()))
    }

    @Test
    fun loadReminders_DisplayedInUI() = mainCoroutineRule.runBlockingTest {
        // GIVEN - There is Reminders in the data source
        repository.saveReminder(reminderDTO1)

        // WHEN - list fragment launched to display reminders
        launchFragmentInContainer<ReminderListFragment>(Bundle.EMPTY, R.style.AppTheme)

        // THEN -  Reminders are Displayed on the screen
        // ProgressBar is not displayed
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
        // RecyclerView has one Reminder = reminderDTO1
        onView(withId(R.id.reminderssRecyclerView)).check(matches(hasChildCount(1)))
        // NoDataText is Not displayed
        onView(withId(R.id.noDataTextView)).check(matches(not(isDisplayed())))
        // FAB is displayed
        onView(withId(R.id.addReminderFAB)).check(matches(isDisplayed()))

    }

    @Test
    fun loadUnavailableReminder_callErrorToDisplay() = mainCoroutineRule.runBlockingTest {
        // GIVEN - There is unavailable Reminder in the data source
        repository.setReturnError(true)

        // WHEN - list fragment launched to display unavailable Reminder
        launchFragmentInContainer<ReminderListFragment>(Bundle.EMPTY, R.style.AppTheme)

        // THEN - snackbar with error msg is Displayed
        onView(withId(snackbar_text))
            .check(matches(withText(repository.errMsg)))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
//            .check(matches(isDisplayed())) => cause error

        // AND - NoDataText is displayed
        onView(withId(R.id.noDataTextView))
            .check(matches(withText(R.string.no_data)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun clickFab_NavigateToSaveReminderFragment() {
        // GIVEN - On list fragment
        val scenario =
            launchFragmentInContainer<ReminderListFragment>(Bundle.EMPTY, R.style.AppTheme)

        val navController = mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - Click on the fab (+)
        onView(withId(R.id.addReminderFAB)).perform(click())

        // THEN - Verify that we navigate to SaveReminderFragment
        verify(navController).navigate(
            ReminderListFragmentDirections.toSaveReminder()
        )

    }

}