package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie


class FavoritesViewModel() : ViewModel() {

    val favorites = MutableLiveData<List<Movie>>()
    val favError = MutableLiveData<Boolean>()
    val favLoading = MutableLiveData<Boolean>()
    private lateinit var movieId: String


    fun getFavs() {


    }
}