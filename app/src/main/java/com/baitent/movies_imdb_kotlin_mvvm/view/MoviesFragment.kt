package com.baitent.movies_imdb_kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.adapter.MovieAdapter
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.MovieListViewModel

class MoviesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }
}
