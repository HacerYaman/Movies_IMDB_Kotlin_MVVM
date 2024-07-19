package com.baitent.movies_imdb_kotlin_mvvm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie


@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favMovie: Movie)

    @Query("SELECT * FROM movie")
    suspend fun getAllFavs(): List<Movie>
}