package com.baitent.movies_imdb_kotlin_mvvm.room

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.FavoritesViewModel

class FavoritesViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FavoritesViewModel(application) as T
    }
}