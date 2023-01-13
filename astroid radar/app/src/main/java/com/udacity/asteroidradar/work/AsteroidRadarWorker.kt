package com.udacity.asteroidradar.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.udacity.asteroidradar.database.AsteroidDatabase.Companion.getInstance
import com.udacity.asteroidradar.repositery.Repository
import com.udacity.asteroidradar.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class AsteroidRadarWorker(context: Context, parameters: WorkerParameters) :
    CoroutineWorker(context, parameters) {

    companion object {
        const val WORKER_NAME = "asteroid_worker"
    }

    override suspend fun doWork(): Result {
        val database = getInstance(applicationContext)
        val repository = Repository(applicationContext, database)
        val today = SimpleDateFormat(Constants.API_QUERY_DATE_FORMAT, Locale.getDefault())
            .format(System.currentTimeMillis())

        return try {
            // clear old data
            repository.clearBeforeToday()
            // download today's data and store it into DB to display
            repository.refreshAsteroids(today,today)
            repository.refreshPicOfDay()

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }

    }
}