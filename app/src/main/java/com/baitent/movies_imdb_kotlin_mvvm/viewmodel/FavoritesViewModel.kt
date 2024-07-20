package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.room.AppDatabase
import kotlinx.coroutines.launch


class FavoritesViewModel(application: Application) : AndroidViewModel(application) {


    private val db: AppDatabase = Room.databaseBuilder(
        application, AppDatabase::class.java, "movie-database"
    ).build()

    private val favoriteMovieDao = db.favoriteMovieDao()

    //tüm favoriler
    val allFavMovies: LiveData<List<Movie>> = favoriteMovieDao.getAllFavsLiveData()

    fun addFavMovie(movie: Movie) {
        viewModelScope.launch {
            favoriteMovieDao.insert(movie)
        }
    }

}