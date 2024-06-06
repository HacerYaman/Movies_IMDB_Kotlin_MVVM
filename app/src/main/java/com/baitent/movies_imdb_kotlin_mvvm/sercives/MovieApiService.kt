package com.baitent.movies_imdb_kotlin_mvvm.sercives

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieApiService {

    private val BASE_URL = "https://api.themoviedb.org/3/"


    private val api_request= Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build().create(MovieService::class.java)

    fun getData(): Single<MovieResponse>{
        return api_request.getPopularMovies(api_key)
    }

}