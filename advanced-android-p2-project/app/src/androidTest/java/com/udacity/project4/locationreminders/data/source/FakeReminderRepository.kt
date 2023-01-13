package com.udacity.project4.locationreminders.data.source

import com.udacity.project4.locationreminders.data.RemindersRepository
import com.udacity.project4.locationreminders.data.Result
import com.udacity.project4.locationreminders.data.source.local.FakeDataSource
import com.udacity.project4.locationreminders.data.source.local.ReminderDTO

class FakeRemindersRepository(private var dataSource : FakeDataSource) : RemindersRepository {


    // Error Flag
    private var shouldReturnError = false

    fun setReturnError(value:Boolean){
        shouldReturnError = value
    }

    // Error Message
    val errMsg = "Test Exception"

    override suspend fun getReminders(): Result<List<ReminderDTO>> {
        if(shouldReturnError) return Result.Error(errMsg)
        return dataSource.getReminders()
    }

    override suspend fun saveReminder(reminder: ReminderDTO): Result<Long> {
        if(shouldReturnError) return Result.Error(errMsg)
        return dataSource.saveReminder(reminder)
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> {
        if(shouldReturnError) return Result.Error(errMsg)
        return dataSource.getReminder(id)
    }

    override suspend fun deleteAllReminders(): Result<Boolean> {
        if(shouldReturnError) return Result.Error(errMsg)
        return dataSource.deleteAllReminders()
    }
}