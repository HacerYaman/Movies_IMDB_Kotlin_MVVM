package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.sercives.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel() : ViewModel() {

    val movieDetail = MutableLiveData<Movie>()
    val movieError = MutableLiveData<Boolean>()
    val movieLoading = MutableLiveData<Boolean>()

    fun getFullDetail(movieId: Int) {
        movieLoading.value = true
        RetrofitClient.apiService.getFullDetail(movieId).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    println("detail başarılı")
                    println(response)
                    val movie = response.body()
                    movie?.let {
                    }
                } else {
                    movieError.value = true
                    println("else düştü")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                movieLoading.value = false
                movieError.value = true
                Log.e("DetailViewModel", "Error: ${t.message}")
            }
        })
    }
}