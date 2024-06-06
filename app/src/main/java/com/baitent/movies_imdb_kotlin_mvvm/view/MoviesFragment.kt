package com.baitent.movies_imdb_kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.adapter.MovieAdapter
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.MovieListViewModel

class MoviesFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        adapter = MovieAdapter(arrayListOf())

       // observeLiveData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView= view.findViewById(R.id.recyclerViewMovie)

      //  viewModel=ViewModelProvider(this).get(MovieListViewModel::class.java)

        recyclerView.layoutManager=GridLayoutManager(context,2)
        recyclerView.adapter=adapter

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { movieResponse ->
            movieResponse?.results?.let { movies ->
                adapter.updateMovieList(movies)
            }
        })
    }
}
