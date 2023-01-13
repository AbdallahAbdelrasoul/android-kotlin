package com.udacity.asteroidradar.screens.main;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0010\u0011B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/udacity/asteroidradar/screens/main/MainListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/udacity/asteroidradar/domain/Asteroid;", "Lcom/udacity/asteroidradar/screens/main/MainListAdapter$ViewHolder;", "clickListener", "Lcom/udacity/asteroidradar/screens/main/MainListAdapter$Companion$AsteroidListener;", "(Lcom/udacity/asteroidradar/screens/main/MainListAdapter$Companion$AsteroidListener;)V", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ViewHolder", "app_debug"})
public final class MainListAdapter extends androidx.recyclerview.widget.ListAdapter<com.udacity.asteroidradar.domain.Asteroid, com.udacity.asteroidradar.screens.main.MainListAdapter.ViewHolder> {
    private final com.udacity.asteroidradar.screens.main.MainListAdapter.Companion.AsteroidListener clickListener = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.udacity.asteroidradar.screens.main.MainListAdapter.Companion Companion = null;
    
    public MainListAdapter(@org.jetbrains.annotations.NotNull()
    com.udacity.asteroidradar.screens.main.MainListAdapter.Companion.AsteroidListener clickListener) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.udacity.asteroidradar.screens.main.MainListAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.udacity.asteroidradar.screens.main.MainListAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/udacity/asteroidradar/screens/main/MainListAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/udacity/asteroidradar/databinding/AsteroidItemBinding;", "(Lcom/udacity/asteroidradar/databinding/AsteroidItemBinding;)V", "bind", "", "item", "Lcom/udacity/asteroidradar/domain/Asteroid;", "clickListener", "Lcom/udacity/asteroidradar/screens/main/MainListAdapter$Companion$AsteroidListener;", "Companion", "app_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.udacity.asteroidradar.databinding.AsteroidItemBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.udacity.asteroidradar.screens.main.MainListAdapter.ViewHolder.Companion Companion = null;
        
        private ViewHolder(com.udacity.asteroidradar.databinding.AsteroidItemBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.udacity.asteroidradar.domain.Asteroid item, @org.jetbrains.annotations.NotNull()
        com.udacity.asteroidradar.screens.main.MainListAdapter.Companion.AsteroidListener clickListener) {
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/udacity/asteroidradar/screens/main/MainListAdapter$ViewHolder$Companion;", "", "()V", "from", "Lcom/udacity/asteroidradar/screens/main/MainListAdapter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "app_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.udacity.asteroidradar.screens.main.MainListAdapter.ViewHolder from(@org.jetbrains.annotations.NotNull()
            android.view.ViewGroup parent) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0005"}, d2 = {"Lcom/udacity/asteroidradar/screens/main/MainListAdapter$Companion;", "", "()V", "AsteroidDiffCallback", "AsteroidListener", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/udacity/asteroidradar/screens/main/MainListAdapter$Companion$AsteroidDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/udacity/asteroidradar/domain/Asteroid;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
        public static final class AsteroidDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.udacity.asteroidradar.domain.Asteroid> {
            
            public AsteroidDiffCallback() {
                super();
            }
            
            @java.lang.Override()
            public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
            com.udacity.asteroidradar.domain.Asteroid oldItem, @org.jetbrains.annotations.NotNull()
            com.udacity.asteroidradar.domain.Asteroid newItem) {
                return false;
            }
            
            @java.lang.Override()
            public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
            com.udacity.asteroidradar.domain.Asteroid oldItem, @org.jetbrains.annotations.NotNull()
            com.udacity.asteroidradar.domain.Asteroid newItem) {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B(\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0004R,\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004\u00a2\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u000e"}, d2 = {"Lcom/udacity/asteroidradar/screens/main/MainListAdapter$Companion$AsteroidListener;", "", "clickListener", "Lkotlin/Function1;", "Lcom/udacity/asteroidradar/domain/Asteroid;", "Lkotlin/ParameterName;", "name", "Asteroid", "", "(Lkotlin/jvm/functions/Function1;)V", "getClickListener", "()Lkotlin/jvm/functions/Function1;", "onClick", "asteroid", "app_debug"})
        public static final class AsteroidListener {
            @org.jetbrains.annotations.NotNull()
            private final kotlin.jvm.functions.Function1<com.udacity.asteroidradar.domain.Asteroid, kotlin.Unit> clickListener = null;
            
            public AsteroidListener(@org.jetbrains.annotations.NotNull()
            kotlin.jvm.functions.Function1<? super com.udacity.asteroidradar.domain.Asteroid, kotlin.Unit> clickListener) {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final kotlin.jvm.functions.Function1<com.udacity.asteroidradar.domain.Asteroid, kotlin.Unit> getClickListener() {
                return null;
            }
            
            public final void onClick(@org.jetbrains.annotations.NotNull()
            com.udacity.asteroidradar.domain.Asteroid asteroid) {
            }
        }
    }
}