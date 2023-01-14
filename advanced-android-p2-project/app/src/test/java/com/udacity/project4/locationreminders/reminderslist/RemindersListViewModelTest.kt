package com.udacity.project4.locationreminders.reminderslist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.udacity.project4.locationreminders.MainCoroutineRule
import com.udacity.project4.locationreminders.data.FakeRemindersRepository
import com.udacity.project4.locationreminders.data.source.local.FakeDataSource
import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RemindersListViewModelTest {
    // Executes each task synchronously (in the same thread) using Architecture Components For Test LiveData
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // For Avoid creating multiple instances of TestCoroutineDispatcher ==> = mainCoroutineRule.runBlockingTest{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val reminderDTO1 = ReminderDTO("a", "b", "c", 1.1, 2.2, "10")
    private val reminderDTO2 = ReminderDTO("a", "b", "c", 1.1, 2.2, "20")
    private val localReminders = listOf(reminderDTO1, reminderDTO2)

    // Fake Dependencies
    private lateinit var localDataSource: FakeDataSource
    private lateinit var repository: FakeRemindersRepository

    // Class Under Test
    private lateinit var viewModel: RemindersListViewModel

    @Before
    fun init() {
        stopKoin()//stop the original app koin

        localDataSource = FakeDataSource(localReminders.toMutableList())
        repository = FakeRemindersRepository(localDataSource)
        viewModel = RemindersListViewModel(ApplicationProvider.getApplicationContext(), repository)
    }

    @Test
    fun loadReminders_getAllReminders_returnWithoutError() = mainCoroutineRule.runBlockingTest{
        // GIVEN - Available Reminders

        mainCoroutineRule.pauseDispatcher()
        // WHEN - Call loadReminders()
        viewModel.loadReminders()

        // ShowLoading is Displayed until reminders is loaded
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(true))
        mainCoroutineRule.resumeDispatcher()

//         THEN - remindersList LiveData Value equals reminders of dataSource
        val value = viewModel.remindersList.getOrAwaitValue()
        assertEquals(value.size , localReminders.size)
//         AND showSnackBar Livedata is not set yet
        assertThat(viewModel.showSnackBar.value, `is`(nullValue()))
        // AND - showNoData LiveData Value is true
        assertEquals(viewModel.showNoData.getOrAwaitValue() , false)
        // AND ShowLoading is not Displayed
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(false))
    }

    @Test
    fun loadReminders_noRemindersFound_returnErrorResult() = mainCoroutineRule.runBlockingTest{
        // GIVEN - No Reminders
        repository.deleteAllReminders()

        mainCoroutineRule.pauseDispatcher()

        // WHEN - Called with return error
        viewModel.loadReminders()

        // ShowLoading is Displayed until reminders is loaded
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(true))
        mainCoroutineRule.resumeDispatcher()

        // THEN - reminderList livedata is not set
        assertThat(viewModel.remindersList.value, `is`(nullValue()))
        // AND - showSnackBar LiveData Value equals error message
        assertEquals(viewModel.showSnackBar.getOrAwaitValue() , "no reminders found!")
        // AND - showNoData LiveData Value is true
        assertEquals(viewModel.showNoData.getOrAwaitValue() , true)
        // AND - ShowLoading is not Displayed
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(false))
    }

    @Test
    fun loadReminders_unavailableReminders_returnErrorResult() = mainCoroutineRule.runBlockingTest{
        // GIVEN - Unavailable Reminders

        mainCoroutineRule.pauseDispatcher()
        // WHEN - Called with return error
        repository.setReturnError(true)
        viewModel.loadReminders()

        // ShowLoading is Displayed until reminders is loaded
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(true))
        mainCoroutineRule.resumeDispatcher()

        // THEN - reminderList livedata is not set
        assertThat(viewModel.remindersList.value, `is`(nullValue()))
        // AND - showSnackBar LiveData Value equals error message
        assertEquals(viewModel.showSnackBar.getOrAwaitValue() , repository.errMsg)
        // AND - showNoData LiveData Value is true
        assertEquals(viewModel.showNoData.getOrAwaitValue() , true)
        // AND - ShowLoading is not Displayed
        assertThat(viewModel.showLoading.getOrAwaitValue(), `is`(false))
    }
}