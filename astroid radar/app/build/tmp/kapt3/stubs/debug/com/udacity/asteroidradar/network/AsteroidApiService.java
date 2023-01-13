package com.udacity.asteroidradar.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J/\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\u0006\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/udacity/asteroidradar/network/AsteroidApiService;", "", "getAsteroids", "", "startDate", "endDate", "apiKey", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPicOfDay", "Lcom/udacity/asteroidradar/network/NetworkPictureOfDay;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AsteroidApiService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "planetary/apod")
    public abstract java.lang.Object getPicOfDay(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.udacity.asteroidradar.network.NetworkPictureOfDay> continuation);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "neo/rest/v1/feed")
    public abstract java.lang.Object getAsteroids(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "start_date")
    java.lang.String startDate, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "end_date")
    java.lang.String endDate, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> continuation);
}