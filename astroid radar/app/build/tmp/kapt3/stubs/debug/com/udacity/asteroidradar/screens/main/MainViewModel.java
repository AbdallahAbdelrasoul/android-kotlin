package com.udacity.asteroidradar.screens.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/udacity/asteroidradar/screens/main/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "asteroids", "Landroidx/lifecycle/LiveData;", "", "Lcom/udacity/asteroidradar/domain/Asteroid;", "getAsteroids", "()Landroidx/lifecycle/LiveData;", "asteroidsToShow", "Landroidx/lifecycle/MutableLiveData;", "", "getAsteroidsToShow", "()Landroidx/lifecycle/MutableLiveData;", "setAsteroidsToShow", "(Landroidx/lifecycle/MutableLiveData;)V", "database", "Lcom/udacity/asteroidradar/database/AsteroidDatabase;", "picOfDay", "Lcom/udacity/asteroidradar/domain/PictureOfDay;", "getPicOfDay", "repository", "Lcom/udacity/asteroidradar/repositery/Repository;", "refreshAsteroidsDatabase", "", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    private final com.udacity.asteroidradar.database.AsteroidDatabase database = null;
    private final com.udacity.asteroidradar.repositery.Repository repository = null;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> asteroidsToShow;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.domain.Asteroid>> asteroids = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.udacity.asteroidradar.domain.PictureOfDay> picOfDay = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getAsteroidsToShow() {
        return null;
    }
    
    public final void setAsteroidsToShow(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.udacity.asteroidradar.domain.Asteroid>> getAsteroids() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.udacity.asteroidradar.domain.PictureOfDay> getPicOfDay() {
        return null;
    }
    
    private final void refreshAsteroidsDatabase(android.content.Context context) {
    }
}