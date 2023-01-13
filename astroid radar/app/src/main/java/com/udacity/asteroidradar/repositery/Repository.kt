package com.udacity.asteroidradar.repositery

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.database.asDomainModel
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.network.ApiService
import com.udacity.asteroidradar.network.asDatabaseModel
import com.udacity.asteroidradar.network.parseAsteroidsJsonResult
import com.udacity.asteroidradar.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Repository(private val context: Context, private val database: AsteroidDatabase) {

    // get the date of today
    private val today = SimpleDateFormat(
        Constants.API_QUERY_DATE_FORMAT,
        Locale.ENGLISH
    ).format(System.currentTimeMillis())

    // Fetch Live ImageOfDay from the DB
    val picOfDay = Transformations.map(database.asteroidDao.getPicOfDay(today)) {
        it?.asDomainModel()
    }

    suspend fun refreshPicOfDay() {
        withContext(Dispatchers.IO) {
            try {
                // get the pic from the server
                val networkPicOFDay = ApiService.retrofitService.getPicOfDay(Constants.API_KEY)
                // insert it to the DB
                networkPicOFDay.media_type.let {
                    if (it.equals("image")) database.asteroidDao.insertPicOfDay(networkPicOFDay.asDatabaseModel())
                }

            } catch (e: Exception) {
                Log.i("getPicOfDay", e.message.toString())
            }
        }
    }

    suspend fun refreshAsteroids(startDate: String, endDate: String) {
        withContext(Dispatchers.IO) {
            try {
                // Download All Of The Asteroids in String Format
                val newStringAsteroids =
                    ApiService.retrofitService.getAsteroids(startDate, endDate, Constants.API_KEY)

                newStringAsteroids.isNotEmpty().let {
                    // Asteroids of the week in Network Object Format
                    val newNetworkAsteroids =
                        parseAsteroidsJsonResult(JSONObject(newStringAsteroids))

                    // Insert Network Asteroids as a Database Asteroids into The DB
                    database.asteroidDao.insertAsteroids(*newNetworkAsteroids.asDatabaseModel())

                }
            } catch (e: Exception) {
                Log.i("refreshAsteroids Error", e.message!!)
            }
        }
    }

    suspend fun clearBeforeToday() {
        withContext(Dispatchers.IO) {
            try {
                // delete data before today
                database.asteroidDao.clearOldAsteroids(today)
                database.asteroidDao.clearOldPics(today)
            } catch (e: Exception) {
                Log.i("clearBeforeToday Error", e.message!!)
            }
        }
    }

    fun getAsteroids(filter: String): LiveData<List<Asteroid>> {
        return when (filter) {
            context.resources.getString(R.string.today_asteroids) -> {
                Transformations.map(database.asteroidDao.getTodayAsteroids(today)) {
                    it.asDomainModel()
                }
            }
            context.resources.getString(R.string.saved_asteroids) -> {
                Transformations.map(database.asteroidDao.getAllAsteroids()) {
                    it.asDomainModel()
                }
            }
            else -> { // Default : Next Week Asteroids
                Transformations.map(database.asteroidDao.getNextWeekAsteroids(today)) {
                    it.asDomainModel()
                }
            }
        }
    }
}
