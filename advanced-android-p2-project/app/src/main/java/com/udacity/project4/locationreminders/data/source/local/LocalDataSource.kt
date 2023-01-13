package com.udacity.project4.locationreminders.data.source.local

import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource internal constructor(
    private val remindersDao: RemindersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ReminderDataSource {

    /**
     * Get the reminders list from the local db
     * @return Result the holds a Success with all the reminders or an Error object with the error message
     */
    override suspend fun getReminders(): Result<List<ReminderDTO>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(remindersDao.getReminders())
            } catch (ex: Exception) {
                Result.Error(ex.localizedMessage)
            }
        }

    /**
     * Insert a reminder in the db.
     * @param reminder the reminder to be inserted
     * @return the new rowId of the inserted item
     */
    override suspend fun saveReminder(reminder: ReminderDTO): Result<Long> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(remindersDao.saveReminder(reminder))
            } catch (ex: Exception) {
                Result.Error(ex.message)
            }
        }

    /**
     * Get a reminder by its id
     * @param id to be used to get the reminder
     * @return Result the holds a Success object with the Reminder or an Error object with the error message
     */
    override suspend fun getReminder(id: String): Result<ReminderDTO> =
        withContext(ioDispatcher) {
            return@withContext try {
                val reminder = remindersDao.getReminderById(id)
                if (reminder != null) {
                    Result.Success(reminder)
                } else {
                    Result.Error("Reminder not found!")
                }
            } catch (e: Exception) {
                Result.Error(e.localizedMessage)
            }
        }

    /**
     * Deletes all the reminders in the db
     */
    override suspend fun deleteAllReminders(): Result<Boolean> =
        withContext(ioDispatcher) {
            return@withContext try {
                remindersDao.deleteAllReminders()
                Result.Success(true)
            } catch (ex: Exception) {
                Result.Error(ex.message)
            }
        }
}