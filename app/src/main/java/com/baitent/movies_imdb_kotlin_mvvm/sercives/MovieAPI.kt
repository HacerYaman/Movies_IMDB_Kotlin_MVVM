package com.baitent.movies_imdb_kotlin_mvvm.sercives

import com.baitent.movies_imdb_kotlin_mvvm.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TMDbApiService {
    @Headers("Authorization: $api_acces_token_long")
    @GET("movie/popular")

    fun getPopularMovies(
       // @Query("language") language: String = "en-US",
        //@Query("page") page: Int = 1
    ): Call<MovieResponse>
}



