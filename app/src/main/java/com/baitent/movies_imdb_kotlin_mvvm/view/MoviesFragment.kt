package com.baitent.movies_imdb_kotlin_mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.adapter.MovieAdapter
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.MovieListViewModel


class MoviesFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    private lateinit var toolbar: Toolbar

    private var isDarkTheme = false


    fun changeTheme() {

        if (isDarkTheme) {
            requireActivity().setTheme(R.style.AppTheme)
            isDarkTheme = false
        } else {
            requireActivity().setTheme(R.style.AppTheme)
            isDarkTheme = true
        }
        requireActivity().recreate()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieListViewModel.getData()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        recyclerView = view.findViewById(R.id.recyclerViewMovie)
        recyclerView.layoutManager = GridLayoutManager(context, 2)



        toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Movies"



        movieListViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                movieAdapter = MovieAdapter(it) {
                    val action =
                        MoviesFragmentDirections.actionMoviesFragmentToDetailFragment(it.id)
                }
                recyclerView.adapter = movieAdapter
                progressBar.visibility = View.GONE
            }
        })

        movieListViewModel.moviesLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        movieListViewModel.moviesError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_theme -> {
                        changeTheme()
                        true
                    }

                    R.id.action_search -> {
                        // Settings item seçildiğinde yapılacak işlemler
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}
