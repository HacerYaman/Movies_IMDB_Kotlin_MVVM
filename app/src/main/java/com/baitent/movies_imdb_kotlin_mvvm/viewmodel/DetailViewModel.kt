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

    fun getFullDetail(movieId: String) {
        movieLoading.value = true
        RetrofitClient.apiService.getFullDetail().enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                movieLoading.value = false
                if (response.isSuccessful) {
                    println("detail okayyy")
                    println(movieDetail)
                    movieDetail.value = response.body()
                    movieError.value = false
                } else {
                    movieError.value = true
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