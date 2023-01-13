package com.udacity.asteroidradar.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007\u001a\n\u0010\b\u001a\u00020\t*\u00020\u0002\u001a\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\u0005*\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a8\u0006\u000b"}, d2 = {"asDatabaseModel", "Lcom/udacity/asteroidradar/database/DatabasePicOfDay;", "Lcom/udacity/asteroidradar/network/NetworkPictureOfDay;", "", "Lcom/udacity/asteroidradar/database/DatabaseAsteroid;", "", "Lcom/udacity/asteroidradar/network/NetworkAsteroid;", "(Ljava/util/List;)[Lcom/udacity/asteroidradar/database/DatabaseAsteroid;", "asDomainModel", "Lcom/udacity/asteroidradar/domain/PictureOfDay;", "Lcom/udacity/asteroidradar/domain/Asteroid;", "app_debug"})
public final class DataTransferObjectsKt {
    
    /**
     * convert Network ImageOfDay to Database ImageOfDay
     */
    @org.jetbrains.annotations.NotNull()
    public static final com.udacity.asteroidradar.database.DatabasePicOfDay asDatabaseModel(@org.jetbrains.annotations.NotNull()
    com.udacity.asteroidradar.network.NetworkPictureOfDay $this$asDatabaseModel) {
        return null;
    }
    
    /**
     * convert Network ImageOfDay to Domain ImageOfDay
     */
    @org.jetbrains.annotations.NotNull()
    public static final com.udacity.asteroidradar.domain.PictureOfDay asDomainModel(@org.jetbrains.annotations.NotNull()
    com.udacity.asteroidradar.network.NetworkPictureOfDay $this$asDomainModel) {
        return null;
    }
    
    /**
     * convert Network Asteroid to Database Asteroid
     */
    @org.jetbrains.annotations.NotNull()
    public static final com.udacity.asteroidradar.database.DatabaseAsteroid[] asDatabaseModel(@org.jetbrains.annotations.NotNull()
    java.util.List<com.udacity.asteroidradar.network.NetworkAsteroid> $this$asDatabaseModel) {
        return null;
    }
    
    /**
     * convert Network Asteroid to Domain Asteroid
     */
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<com.udacity.asteroidradar.domain.Asteroid> asDomainModel(@org.jetbrains.annotations.NotNull()
    java.util.List<com.udacity.asteroidradar.network.NetworkAsteroid> $this$asDomainModel) {
        return null;
    }
}