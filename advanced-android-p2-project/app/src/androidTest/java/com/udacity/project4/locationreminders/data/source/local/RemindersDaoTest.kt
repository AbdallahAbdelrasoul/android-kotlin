package com.udacity.project4.locationreminders.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.udacity.project4.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
//Unit test the DAO
@SmallTest
class RemindersDaoTest {
    // Executes each task synchronously (in the same thread) using Architecture Components
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // For Avoid creating multiple instances of TestCoroutineDispatcher ==> = mainCoroutineRule.runBlockingTest{
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val reminder1 = ReminderDTO("tit1","desc1","loca1",1.1,1.2,"id1")
    private val reminder2 = ReminderDTO("tit2","desc2","loca2",2.1,2.2,"id2")
    private lateinit var database: RemindersDatabase

    @Before
    fun initDB() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RemindersDatabase::class.java
        ).build()
    }
    @After
    fun closeDB() = database.close()

    @Test
    fun saveReminder_getById() = mainCoroutineRule.runBlockingTest{
        // GIVEN - insert a reminder
        database.reminderDao().saveReminder(reminder1)
        // WHEN - get the reminder by id from the db
        val loaded = database.reminderDao().getReminderById(reminder1.id)
        // THEN - loaded reminder is the inserted one
        assertThat(loaded as ReminderDTO, notNullValue())
        assertThat(loaded.id, `is`(reminder1.id))
        assertThat(loaded.title, `is`(reminder1.title))
        assertThat(loaded.description, `is`(reminder1.description))
        assertThat(loaded.location, `is`(reminder1.location))
    }

    @Test
    fun deleteAllReminders_getAllReminders() = mainCoroutineRule.runBlockingTest{
        // GIVEN - two reminders in the db
        database.reminderDao().saveReminder(reminder1)
        database.reminderDao().saveReminder(reminder2)
        // WHEN - get the all reminders from the db
        val loaded = database.reminderDao().getReminders()
        // THEN - loaded reminders from the dbs are the inserted ones
        assertThat(loaded.size, `is` (2))
        assertThat(loaded[0].id, `is`(reminder1.id))
        assertThat(loaded[1].id, `is`(reminder2.id))
        // WHEN - delete the all reminders from the db
        database.reminderDao().deleteAllReminders()
        // THEN - there is no reminders in the dbs
        val noLoaded = database.reminderDao().getReminders()
        assertThat(noLoaded , `is`(emptyList()))
    }
}