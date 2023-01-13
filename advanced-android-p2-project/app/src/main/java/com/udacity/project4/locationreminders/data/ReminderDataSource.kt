package com.udacity.project4.locationreminders.data

import com.udacity.project4.locationreminders.data.source.local.ReminderDTO
import com.udacity.project4.locationreminders.data.Result

/**
 * Main entry point for accessing reminders data.
 */
interface ReminderDataSource {
    suspend fun getReminders(): Result<List<ReminderDTO>>
    suspend fun saveReminder(reminder: ReminderDTO) : Result<Long>
    suspend fun getReminder(id: String): Result<ReminderDTO>
    suspend fun deleteAllReminders():Result<Boolean>
}