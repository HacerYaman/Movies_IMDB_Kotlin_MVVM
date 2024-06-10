package com.baitent.movies_imdb_kotlin_mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.baitent.movies_imdb_kotlin_mvvm.model.MovieResponse
import com.baitent.movies_imdb_kotlin_mvvm.sercives.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val call = RetrofitClient.apiService.getPopularMovies()

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    val movieResponse = response.body()
                    movieResponse?.results?.forEach { movie ->
                        Log.d("MainActivity", "Movie: ${movie.title}, Rating: ${movie.vote_average}")
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("MainActivity", "Error: ${t.message}")
            }
        })
    }
}