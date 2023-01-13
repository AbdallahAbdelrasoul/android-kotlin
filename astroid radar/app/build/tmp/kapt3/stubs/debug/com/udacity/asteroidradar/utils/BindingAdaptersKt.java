package com.udacity.asteroidradar.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 2, d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u001a\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007\u001a \u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eH\u0007\u001a\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007\u001a\u0018\u0010\u0015\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007\u001a\u0018\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0007\u00a8\u0006\u0017"}, d2 = {"bindAsteroidStatusImage", "", "imageView", "Landroid/widget/ImageView;", "isHazardous", "", "bindDetailsStatusImage", "bindImg", "pic", "Lcom/udacity/asteroidradar/domain/PictureOfDay;", "bindRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "data", "", "Lcom/udacity/asteroidradar/domain/Asteroid;", "bindTextViewToAstronomicalUnit", "textView", "Landroid/widget/TextView;", "number", "", "bindTextViewToDisplayVelocity", "bindTextViewToKmUnit", "app_debug"})
public final class BindingAdaptersKt {
    
    @androidx.databinding.BindingAdapter(value = {"setImg"})
    public static final void bindImg(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, @org.jetbrains.annotations.Nullable()
    com.udacity.asteroidradar.domain.PictureOfDay pic) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"listData"})
    public static final void bindRecyclerView(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView, @org.jetbrains.annotations.Nullable()
    java.util.List<com.udacity.asteroidradar.domain.Asteroid> data) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"statusIcon"})
    public static final void bindAsteroidStatusImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, boolean isHazardous) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"asteroidStatusImage"})
    public static final void bindDetailsStatusImage(@org.jetbrains.annotations.NotNull()
    android.widget.ImageView imageView, boolean isHazardous) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"astronomicalUnitText"})
    public static final void bindTextViewToAstronomicalUnit(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, double number) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"kmUnitText"})
    public static final void bindTextViewToKmUnit(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, double number) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"velocityText"})
    public static final void bindTextViewToDisplayVelocity(@org.jetbrains.annotations.NotNull()
    android.widget.TextView textView, double number) {
    }
}