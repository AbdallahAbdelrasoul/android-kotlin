package com.udacity.project4.locationreminders.data

import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import com.udacity.project4.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.*

/**
 * Concrete implementation of a data source as a db.
 *
 * The repository is implemented so that you can focus on only testing it.
 *
 * @param remindersDao the dao that does the Room db operations
 * @param ioDispatcher a coroutine dispatcher to offload the blocking IO tasks
 */
class DefaultRemindersRepository(
    private val remindersDataSource: ReminderDataSource
) : RemindersRepository {

    /**
     * Get the reminders list from the local db
     * @return Result the holds a Success with all the reminders or an Error object with the error message
     */
    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        wrapEspressoIdlingResource {
            return remindersDataSource.getReminders()
        }
    }


    /**
     * Insert a reminder in the db.
     * @param reminder the reminder to be inserted
     * @return the new rowId of the inserted item
     */
    override suspend fun saveReminder(reminder: ReminderDTO): Result<Long> {
        wrapEspressoIdlingResource {
            return remindersDataSource.saveReminder(reminder)
        }
    }

    /**
     * Get a reminder by its id
     * @param id to be used to get the reminder
     * @return Result the holds a Success object with the Reminder or an Error object with the error message
     */
    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        wrapEspressoIdlingResource {
            return remindersDataSource.getReminder(id)
        }
    }

    /**
     * Deletes all the reminders in the db
     */
    override suspend fun deleteAllReminders(): Result<Boolean> {
       wrapEspressoIdlingResource {
           return remindersDataSource.deleteAllReminders()
       }
    }
}
