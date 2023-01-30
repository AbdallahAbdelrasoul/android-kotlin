package com.udacity.project4.locationreminders.data.source.local

import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.Result

//Use FakeDataSource that acts as a test double to the LocalDataSource
class FakeDataSource(var reminders: MutableList<ReminderDTO> = mutableListOf()) :
    ReminderDataSource {

    // Error Flag
    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    // Error Message
    val errMsg = "Test Exception"

    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        if (shouldReturnError) return Result.Error(errMsg)

        return try {
            val reminders = ArrayList(reminders)
            if (reminders.isNotEmpty())
                Result.Success(reminders)
            else
                Result.Error("no reminders found!")
        } catch (ex: Exception) {
            Result.Error(ex.localizedMessage)
        }

    }

    override suspend fun saveReminder(reminder: ReminderDTO): Result<Long> {
        if (shouldReturnError) return Result.Error(errMsg)
        if (reminders.add(reminder)) {
            val saved = reminders.indexOf(reminder).toLong()
            return if (saved != -1L)
                Result.Success(saved)
            else
                Result.Error("Reminder Not Saved")
        } else {
            return Result.Error("Reminder Not Saved")
        }
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        if (shouldReturnError) return Result.Error(errMsg)
        try {
            val reminder = reminders.find { it.id == id }
            return if (reminder != null) {
                Result.Success(reminder)
            } else {
                Result.Error("Reminder Not Found")
            }
        } catch (ex: Exception) {
            return Result.Error(ex.message)
        }
    }

    override suspend fun deleteAllReminders(): Result<Boolean> {
        if (shouldReturnError) return Result.Error(errMsg)
        return try {
            reminders.clear()
            return Result.Success(true)
        } catch (ex: Exception) {
            Result.Error(ex.message)
        }
    }

}