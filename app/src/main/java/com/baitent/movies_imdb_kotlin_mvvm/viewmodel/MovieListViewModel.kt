package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baitent.movies_imdb_kotlin_mvvm.model.MovieModel

class MovieListViewModel : ViewModel(){

    val movies= MutableLiveData<List<MovieModel>>()
    val moviesError = MutableLiveData<Boolean>()
    val moviesLoading = MutableLiveData<Boolean>()


    fun loadData(){

        val movie = MovieModel("Inception", "url1", "Movie", "4.6", "description1", "urlPoster1")
        val movie2 = MovieModel("Some Movie", "url2", "Series", "4.6", "description2", "urlPoster2")
        val movie3 = MovieModel("Another Movie", "url3", "Documentary", "4.6", "description3", "urlPoster3")

        val movieList = arrayListOf(movie, movie2, movie3)

        movies.value = movieList
    }

}