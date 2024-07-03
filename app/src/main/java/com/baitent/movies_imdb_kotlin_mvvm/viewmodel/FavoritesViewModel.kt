package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    val favorites = MutableLiveData<List<Movie>>()
    val favError = MutableLiveData<Boolean>()
    val favLoading = MutableLiveData<Boolean>()
    private lateinit var movieId: String


    private val db: AppDatabase = Room.databaseBuilder(
        application, AppDatabase::class.java, "movie-database"
    ).build()

    fun addForiveteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            db.favoriteMovieDao().insert(movie)
        }
    }

    fun getFavs() {


    }
}