package com.udacity.asteroidradar.network

import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.database.DatabaseAsteroid
import com.udacity.asteroidradar.database.DatabasePicOfDay
import com.udacity.asteroidradar.domain.Asteroid
import com.udacity.asteroidradar.domain.PictureOfDay

@JsonClass(generateAdapter = true)
data class NetworkAsteroid(
    val id: Long,
    val codename: String?,
    val closeApproachDate: String?,
    val absoluteMagnitude: Double?,
    val estimatedDiameter: Double?,
    val relativeVelocity: Double?,
    val distanceFromEarth: Double?,
    val isPotentiallyHazardous: Boolean?
)

@JsonClass(generateAdapter = true)
data class NetworkPictureOfDay(
    val date:String,
    val media_type: String?,
    val title: String?,
    val url: String
)

/**
 * convert Network ImageOfDay to Database ImageOfDay
 */
fun NetworkPictureOfDay.asDatabaseModel(): DatabasePicOfDay {
    return DatabasePicOfDay(
        date = date,
        mediaType = media_type,
        title = title,
        url = url
    )
}
/**
 * convert Network ImageOfDay to Domain ImageOfDay
 */
fun NetworkPictureOfDay.asDomainModel(): PictureOfDay {
    return PictureOfDay(
        date = date,
        mediaType = media_type,
        title = title,
        url = url
    )
}

/**
 * convert Network Asteroid to Database Asteroid
 */
fun List<NetworkAsteroid>.asDatabaseModel(): Array<DatabaseAsteroid> {
    return map {
        DatabaseAsteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }.toTypedArray()
}

/**
 * convert Network Asteroid to Domain Asteroid
 */
fun List<NetworkAsteroid>.asDomainModel(): List<Asteroid> {
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