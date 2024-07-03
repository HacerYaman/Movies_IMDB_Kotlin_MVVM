package com.baitent.movies_imdb_kotlin_mvvm.room

import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao(): FavoriteMovieDao

}