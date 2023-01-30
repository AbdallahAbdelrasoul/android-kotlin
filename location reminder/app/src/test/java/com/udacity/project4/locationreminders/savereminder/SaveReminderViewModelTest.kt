package com.udacity.project4.locationreminders.savereminder

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.R
import com.udacity.project4.base.NavigationCommand
import com.udacity.project4.locationreminders.MainCoroutineRule
import com.udacity.project4.locationreminders.data.FakeRemindersRepository
import com.udacity.project4.locationreminders.data.Result
import com.udacity.project4.locationreminders.data.source.local.FakeDataSource
import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SaveReminderViewModelTest {
    // Executes each task synchronously (in the same thread) using Architecture Components For Test LiveData
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // For Avoid creating multiple instances of TestCoroutineDispatcher ==> = mainCoroutineRule.runBlockingTest{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val reminderDTO1 = ReminderDTO("a", "b", "c", 1.1, 2.2, "10")
    private val reminderDTO2 = ReminderDTO("a", "b", "c", 1.1, 2.2, "20")
    private val reminderDTO3 = ReminderDataItem("a", "b", "c", 1.1, 2.2, "30")
    private val localReminders = listOf(reminderDTO1, reminderDTO2)

    // Fake Dependencies
    private lateinit var localDataSource: FakeDataSource
    private lateinit var repository: FakeRemindersRepository

    // Class Under Test
    private lateinit var viewModel: SaveReminderViewModel
    private lateinit var appContext: Application

    @Before
    fun createViewModel() {
        stopKoin()//stop the original app koin

        localDataSource = FakeDataSource(localReminders.toMutableList())
        repository = FakeRemindersRepository(localDataSource)
        appContext = ApplicationProvider.getApplicationContext()
        viewModel = SaveReminderViewModel(appContext, repository)
    }

    @Test
    fun validateAndSaveReminder_insertedInLocalDataSource() = mainCoroutineRule.runBlockingTest {
        // GIVEN - Two Reminders in the dataSource

        // WHEN - save a new Reminder
        viewModel.validateAndSaveReminder(reminderDTO3)

        // THEN - reminder is saved in the dataSource and size become 3
        val newList = repository.getReminders() as Result.Success
        assertEquals(newList.data.size, 3)
    }

    @Test
    fun validateAndSaveReminder_saveReminder_withoutError() {
        // Pause dispatcher so you can verify initial values.
        mainCoroutineRule.pauseDispatcher()

        // Save a new Reminder with exception
        viewModel.validateAndSaveReminder(reminderDTO3)

        // Then assert that the progress indicator is shown.
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(true))

        // Execute pending coroutines actions.
        mainCoroutineRule.resumeDispatcher()

        // test LiveData objects
        assertThat(viewModel.reminderSaved.getOrAwaitValue(), not(-1L))
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(false))
        assertThat(viewModel.showToast.getOrAwaitValue(), `is`(appContext.getString(R.string.reminder_saved)))
        assertEquals(viewModel.navigationCommand.getOrAwaitValue(), (NavigationCommand.Back))
    }

    @Test
    fun validateAndSaveReminder_notSaveReminder_withError() {
        // Pause dispatcher so you can verify initial values.
        mainCoroutineRule.pauseDispatcher()

        // Save a new Reminder with Exception
        repository.setReturnError(true)
        viewModel.validateAndSaveReminder(reminderDTO3)

        // Then assert that the progress indicator is shown.
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(true))

        // Execute pending coroutines actions.
        mainCoroutineRule.resumeDispatcher()

        // test LiveData objects
        assertThat(viewModel.reminderSaved.getOrAwaitValue(), `is`(-1L))
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(false))
        assertThat(viewModel.showToast.getOrAwaitValue(), `is`(repository.errMsg))
        assertThat(viewModel.navigationCommand.value, `is`(nullValue()))
    }
}