package com.baitent.movies_imdb_kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.adapter.MovieAdapter
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.MovieListViewModel

class MoviesFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        adapter = MovieAdapter(arrayListOf())
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

        viewModel=ViewModelProvider(this).get(MovieListViewModel::class.java)
        viewModel.loadData()

        recyclerView.layoutManager=GridLayoutManager(context,2)
        recyclerView.adapter=adapter

        observeLiveData()
        viewModel.loadData()
    }

    fun observeLiveData(){
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies -> movies?.let {

                recyclerView.visibility=View.VISIBLE
                adapter.updateMovieList(movies)         //BU KESİNLİKLE GEREKLİ YOKSA GÖRÜNMÜYO

        }
        })
    }
}