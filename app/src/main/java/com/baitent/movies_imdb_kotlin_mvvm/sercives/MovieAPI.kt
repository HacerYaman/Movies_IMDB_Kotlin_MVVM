package com.baitent.movies_imdb_kotlin_mvvm.sercives

import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApiService {
    @Headers("Authorization: $api_acces_token_long")
    @GET("movie/popular")

    fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 5
    ): Call<MovieResponse>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZWQyOWY4ZDQxNDRkNjY3ODczYmY5MzaW9uIjoxfQ.X_ysKiMtmoBn5GtBTeKGcS-miXV2DAXNhjLgk51mKc0")
    @GET("movie/{movie_id}")
    fun getFullDetail(
        @Path("movie_id") movieId: Int,
        @Query("language") language: String = "en-US"
    ): Call<Movie>

}



