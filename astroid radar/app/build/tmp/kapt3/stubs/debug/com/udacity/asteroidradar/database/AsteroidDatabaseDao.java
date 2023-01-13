package com.udacity.asteroidradar.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tH\'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\r\u001a\u00020\u0005H\'J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\t2\u0006\u0010\u0010\u001a\u00020\u0005H\'J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\u0004\u001a\u00020\u0005H\'J%\u0010\u0012\u001a\u00020\u00032\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0014\"\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/udacity/asteroidradar/database/AsteroidDatabaseDao;", "", "clearOldAsteroids", "", "today", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearOldPics", "getAllAsteroids", "Landroidx/lifecycle/LiveData;", "", "Lcom/udacity/asteroidradar/database/DatabaseAsteroid;", "getNextWeekAsteroids", "startDate", "getPicOfDay", "Lcom/udacity/asteroidradar/database/DatabasePicOfDay;", "dayDate", "getTodayAsteroids", "insertAsteroids", "asteroids", "", "([Lcom/udacity/asteroidradar/database/DatabaseAsteroid;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPicOfDay", "pic", "(Lcom/udacity/asteroidradar/database/DatabasePicOfDay;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AsteroidDatabaseDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertAsteroids(@org.jetbrains.annotations.NotNull()
    com.udacity.asteroidradar.database.DatabaseAsteroid[] asteroids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from asteroids_table where closeApproachDate >= :startDate order by closeApproachDate ASC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.database.DatabaseAsteroid>> getNextWeekAsteroids(@org.jetbrains.annotations.NotNull()
    java.lang.String startDate);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from asteroids_table where closeApproachDate = :today")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.database.DatabaseAsteroid>> getTodayAsteroids(@org.jetbrains.annotations.NotNull()
    java.lang.String today);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from asteroids_table order by closeApproachDate ASC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.database.DatabaseAsteroid>> getAllAsteroids();
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertPicOfDay(@org.jetbrains.annotations.NotNull()
    com.udacity.asteroidradar.database.DatabasePicOfDay pic, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from pic_of_day_table where date = :dayDate")
    public abstract androidx.lifecycle.LiveData<com.udacity.asteroidradar.database.DatabasePicOfDay> getPicOfDay(@org.jetbrains.annotations.NotNull()
    java.lang.String dayDate);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "delete from asteroids_table where closeApproachDate < :today")
    public abstract java.lang.Object clearOldAsteroids(@org.jetbrains.annotations.NotNull()
    java.lang.String today, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "delete from pic_of_day_table where date < :today")
    public abstract java.lang.Object clearOldPics(@org.jetbrains.annotations.NotNull()
    java.lang.String today, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}