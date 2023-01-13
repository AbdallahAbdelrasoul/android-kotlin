package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDatabaseDao {

    // Insert Downloaded Asteroids into my DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsteroids(vararg asteroids: DatabaseAsteroid)

    // Fetch the asteroids from today onwards, sorted by date
    @Query(
        "select * from asteroids_table " +
                "where closeApproachDate >= :startDate " +
                "order by closeApproachDate ASC"
    )
    fun getNextWeekAsteroids(startDate: String): LiveData<List<DatabaseAsteroid>>

    // Fetch the asteroids of today only
    @Query("select * from asteroids_table where closeApproachDate = :today")
    fun getTodayAsteroids(today: String): LiveData<List<DatabaseAsteroid>>

    // Fetch all saved Asteroids in DB
    @Query("select * from asteroids_table order by closeApproachDate ASC")
    fun getAllAsteroids():LiveData<List<DatabaseAsteroid>>

    // Insert a PicOfDay into DB
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicOfDay(pic: DatabasePicOfDay)

    // Fetch PicOfDay
    @Query("select * from pic_of_day_table where date = :dayDate")
    fun getPicOfDay(dayDate: String): LiveData<DatabasePicOfDay>

    // Clear asteroids before today data
    @Query("delete from asteroids_table where closeApproachDate < :today")
    suspend fun clearOldAsteroids(today: String)

    // Clear pictures of day before today data
    @Query("delete from pic_of_day_table where date < :today")
    suspend fun clearOldPics(today: String)
}


@Database(
    entities = [DatabaseAsteroid::class, DatabasePicOfDay::class],
    version = 2,
    exportSchema = false
)
abstract class AsteroidDatabase : RoomDatabase() {

    abstract val asteroidDao: AsteroidDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: AsteroidDatabase? = null

        fun getInstance(context: Context): AsteroidDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        "asteroid_app_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}