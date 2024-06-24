package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.model.MovieResponse
import com.baitent.movies_imdb_kotlin_mvvm.sercives.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListViewModel() : ViewModel() {

    val movies = MutableLiveData<List<Movie>>()
    val moviesError = MutableLiveData<Boolean>()
    val moviesLoading = MutableLiveData<Boolean>()

    fun getData() {
        RetrofitClient.apiService.getPopularMovies().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    println("başarılı")
                    val movieResponse = response.body()
                    movieResponse?.let {
                        movies.value = it.results
                        moviesError.value = false
                    }
                } else {
                    moviesError.value = true
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                moviesLoading.value = false
                moviesError.value = true
                Log.e("MovieListViewModel", "Error: ${t.message}")
            }
        })
    }
}