package com.udacity.project4.locationreminders.data.source

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.udacity.project4.MainCoroutineRule
import com.udacity.project4.locationreminders.data.DefaultRemindersRepository
import com.udacity.project4.locationreminders.data.Result
import com.udacity.project4.locationreminders.data.source.local.LocalDataSource
import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import com.udacity.project4.locationreminders.data.source.local.RemindersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.IsEqual
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Medium Test to test the repository
@MediumTest
class DefaultRemindersRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val reminderDTO1 = ReminderDTO("a", "b", "c", 1.1, 2.2, "10")
    private val reminderDTO2 = ReminderDTO("a", "b", "c", 1.1, 2.2, "20")

    private lateinit var database: RemindersDatabase
    private lateinit var localDataSource: LocalDataSource

    // Class Under Test
    private lateinit var reminderRepo: DefaultRemindersRepository

    @Before
    fun createRepo() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RemindersDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        localDataSource = LocalDataSource(database.reminderDao(), Dispatchers.Main)
        reminderRepo = DefaultRemindersRepository(localDataSource)

        runBlocking {
            reminderRepo.deleteAllReminders()
        }
    }

    @After
    fun closeDB() = database.close()

    @Test
    fun getReminders_RemindersFoundInDB() = runBlocking {
        // GIVEN - there is reminder saved in DB
        reminderRepo.saveReminder(reminderDTO1) as Result.Success
        reminderRepo.saveReminder(reminderDTO2) as Result.Success

        // WHEN - try to get Reminders
        val reminders = reminderRepo.getReminders() as Result.Success

        // THEN - it returned successfully
        assertThat(reminders.data[0].id, IsEqual(reminderDTO1.id))
        assertThat(reminders.data[1].id, IsEqual(reminderDTO2.id))
    }

    @Test
    fun getReminders_NotRemindersFoundInDB() = runBlocking {
        // GIVEN - no Reminders in DB

        // WHEN - try to get Reminders
        val reminders = reminderRepo.getReminders() as Result.Error

        // THEN - get Error with this message
        assertThat(reminders.message, `is`("no reminders found!"))
    }

    @Test
    fun saveReminder_insertReminderIntoLocalDataSource() = runBlocking {
        // GIVEN - save one Reminder in DB
        val inserted = reminderRepo.saveReminder(reminderDTO1) as Result.Success

        // WHEN - returned value != -1
        if (inserted.data != -1L) {

            // THEN - is inserted successfully
            val reminder = reminderRepo.getReminder(reminderDTO1.id) as Result.Success
            assertThat(reminder.data.id, IsEqual(reminderDTO1.id))
        }
    }

    @Test
    fun saveReminder_insertError_reminderNotSaved() = runBlocking {
        // GIVEN - save one Reminder in DB
        val inserted = reminderRepo.saveReminder(reminderDTO1) as Result.Success

        // WHEN - returned value == -1
        if (inserted.data == -1L) {

            // THEN - is not inserted and get Error
            val reminder = reminderRepo.getReminder(reminderDTO1.id) as Result.Error
            assertThat(reminder.message, IsEqual("Reminder not found!"))
        }
    }


    @Test
    fun getReminder_getReminderByIdFromLocalDataSource() = runBlocking {
        // GIVEN - there is reminder saved in DB
        reminderRepo.saveReminder(reminderDTO1) as Result.Success

        // WHEN - try to get it successfully
        val reminder = reminderRepo.getReminder(reminderDTO1.id) as Result.Success

        // THEN - return the exact reminder
        assertThat(reminder.data.id, IsEqual(reminderDTO1.id))
        assertThat(reminder.data.title, IsEqual(reminderDTO1.title))
        assertThat(reminder.data.description, IsEqual(reminderDTO1.description))
        assertThat(reminder.data.location, IsEqual(reminderDTO1.location))
        assertThat(reminder.data.latitude, IsEqual(reminderDTO1.latitude))
        assertThat(reminder.data.longitude, IsEqual(reminderDTO1.longitude))
    }

    @Test
    fun getReminder_NotFound_Error() = runBlocking {
        // GIVEN - NO Reminders in the DB

        // WHEN - try to get reminder with specific id
        val reminder = reminderRepo.getReminder(reminderDTO1.id) as Result.Error

        // THEN - not found and return error msg
        assertThat(reminder.message, `is`("Reminder not found!"))
    }

    @Test
    fun deleteAllReminders_DeleteAllRemindersFromLocalDataSource() =
        runBlocking {
            // GIVEN - there is reminder saved in DB
            reminderRepo.saveReminder(reminderDTO1) as Result.Success

            // WHEN - delete all reminders
            val deleted = reminderRepo.deleteAllReminders() as Result.Success

            // successfully deleted , THEN - return error with no data message
            if (deleted.data) {
                val reminders = reminderRepo.getReminders() as Result.Error
                assertThat(reminders.message, `is`("no reminders found!"))
            } else {
                // not deleted , THEN - reminder still in DB
                val reminders = reminderRepo.getReminders() as Result.Success
                assertEquals(reminders.data, `is`(not(emptyList<ReminderDTO>())))
            }
        }

}