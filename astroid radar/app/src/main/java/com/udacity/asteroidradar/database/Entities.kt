package com.udacity.asteroidradar.database

import androidx.lifecycle.Transformations
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay

@Entity(tableName = "asteroids_table")
data class DatabaseAsteroid(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val codename: String?,
    val closeApproachDate: String?,
    val absoluteMagnitude: Double?,
    val estimatedDiameter: Double?,
    val relativeVelocity: Double?,
    val distanceFromEarth: Double?,
    val isPotentiallyHazardous: Boolean?
)

fun List<DatabaseAsteroid>.asDomainModel(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}

@Entity(tableName = "pic_of_day_table")
data class DatabasePicOfDay(
    @PrimaryKey
    val date: String,
    val url: String,
    val mediaType: String?,
    val title: String?
)

fun DatabasePicOfDay.asDomainModel() : PictureOfDay {
    return PictureOfDay(
        date = date,
        url = url,
        mediaType = mediaType,
        title = title
    )
}