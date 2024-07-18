package com.baitent.movies_imdb_kotlin_mvvm.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao
}
