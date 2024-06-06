package com.baitent.movies_imdb_kotlin_mvvm.sercives

import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService  {

    //GET
    @GET("movie/popular")
    fun getPopularMovies(@Query(api_key) apiKey: String): Single<MovieResponse>

}
data class MovieResponse(
    @SerializedName("results") val results: List<Movie>
)




