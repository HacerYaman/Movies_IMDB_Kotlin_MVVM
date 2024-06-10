package com.baitent.movies_imdb_kotlin_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


class MovieListViewModel() : ViewModel() {

    val moviesError = MutableLiveData<Boolean>()
    val moviesLoading = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()


}
