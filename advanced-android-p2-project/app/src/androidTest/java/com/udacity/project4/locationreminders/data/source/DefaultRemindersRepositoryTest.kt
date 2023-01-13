package com.udacity.project4.locationreminders.data.source

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.MainCoroutineRule
import com.udacity.project4.locationreminders.data.DefaultRemindersRepository
import com.udacity.project4.locationreminders.data.Result
import com.udacity.project4.locationreminders.data.source.local.FakeDataSource
import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Medium Test to test the repository
@MediumTest
class DefaultRemindersRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val reminderDTO1 = ReminderDTO("a","b","c",1.1,2.2,"10")
    private val reminderDTO2 = ReminderDTO("a","b","c",1.1,2.2,"20")
    private val localReminders = listOf(reminderDTO1,reminderDTO2)
    private val reminderDTO3 = ReminderDTO("a","b","c",1.1,2.2,"30")

    // Fake Dependencies
    private lateinit var localDataSource : FakeDataSource

    // Class Under Test
    private lateinit var reminderRepo : DefaultRemindersRepository

    @Before
    fun createRepo(){
        localDataSource = FakeDataSource(localReminders.toMutableList())
        reminderRepo = DefaultRemindersRepository(localDataSource)
    }

    @Test
    fun getReminders_getAllRemindersFromLocalDataSource() = mainCoroutineRule.runBlockingTest {
        val reminders = reminderRepo.getReminders() as Result.Success

        Assert.assertThat(reminders.data, IsEqual(localReminders))
    }

    @Test
    fun saveReminder_insertReminderIntoLocalDataSource() = mainCoroutineRule.runBlockingTest {
        val inserted = reminderRepo.saveReminder(reminderDTO3) as Result.Success

        Assert.assertNotEquals(inserted.data, -1L)
    }

    @Test
    fun getReminder_getReminderByIdFromLocalDataSource() = mainCoroutineRule.runBlockingTest {
        val id = "20"
        val reminder = reminderRepo.getReminder(id) as Result.Success

        assertThat(reminder.data, IsEqual(reminderDTO2))
    }

    @Test
    fun deleteAllReminders_DeleteAllRemindersFromLocalDataSource() = mainCoroutineRule.runBlockingTest {
        reminderRepo.deleteAllReminders()
        val reminders = reminderRepo.getReminders() as Result.Success

        assertEquals(reminders.data, emptyList<ReminderDTO>())
    }

}