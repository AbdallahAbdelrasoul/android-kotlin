package com.udacity.asteroidradar.screens.main

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.*
import com.udacity.asteroidradar.database.AsteroidDatabase
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay
import com.udacity.asteroidradar.repositery.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(context: Context) : ViewModel() {

    // Get database instance using the context Constructor Parameter
    private val database = AsteroidDatabase.getInstance(context)

    // Create a repository instance using database
    private val repository = Repository(context, database)

    var asteroidsToShow = MutableLiveData<String>()

    val asteroids: LiveData<List<Asteroid>> = asteroidsToShow.switchMap {
        repository.getAsteroids(it)
    }

    // Live ImageOfDay
    val picOfDay: LiveData<PictureOfDay?>

    init {
        // Download latest Asteroids from the internet and insert them into the DB
        refreshAsteroidsDatabase(context)

        // set the Live Asteroids to the next week asteroids
        asteroidsToShow.value = ""

        // set the Live ImageOfDay
        picOfDay = repository.picOfDay
    }

    private fun refreshAsteroidsDatabase(context: Context) {
        viewModelScope.launch {
            try {
                // refresh all Asteroids
                repository.refreshAsteroids("", "")
//                Toast.makeText(context,repository.domainAsteroids.value.toString(),Toast.LENGTH_SHORT).show()

                repository.refreshPicOfDay()
//                Toast.makeText(context,repository.picOfDay.value.toString(),Toast.LENGTH_SHORT).show()

            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
