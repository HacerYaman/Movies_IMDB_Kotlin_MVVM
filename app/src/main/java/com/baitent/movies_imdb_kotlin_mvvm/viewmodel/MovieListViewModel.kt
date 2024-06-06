package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.sercives.MovieApiService
import com.baitent.movies_imdb_kotlin_mvvm.sercives.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MovieListViewModel : ViewModel(){

    val movies2= MutableLiveData<List<Movie>>()
    val movies= MutableLiveData<MovieResponse?>()
    val moviesError = MutableLiveData<Boolean>()
    val moviesLoading = MutableLiveData<Boolean>()

    private val movieService = MovieApiService()
    private val disposable = CompositeDisposable()

    init {
        getMoviesFromApi()
    }

    private fun getMoviesFromApi() {
        moviesLoading.value=true

        disposable.add(
            movieService.getData().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(value: MovieResponse?) {
                        movies.value = value
                        moviesLoading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        moviesError.value = true
                        moviesLoading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}