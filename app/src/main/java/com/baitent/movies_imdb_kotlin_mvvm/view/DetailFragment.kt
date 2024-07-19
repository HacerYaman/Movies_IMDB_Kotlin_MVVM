package com.baitent.movies_imdb_kotlin_mvvm.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.room.FavoritesViewModelFactory
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.DetailViewModel
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.FavoritesViewModel
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private val favoriteViewModel: FavoritesViewModel by viewModels {
        FavoritesViewModelFactory(requireActivity().application)
    }
    private lateinit var progressBar: ProgressBar
    private lateinit var moviePoster: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieRating: TextView
    private lateinit var movieDescription: TextView
    private lateinit var toolbar: Toolbar
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var movieId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        progressBar = view.findViewById(R.id.progressBar2)
        moviePoster = view.findViewById(R.id.movie_poster)
        movieTitle = view.findViewById(R.id.textView)
        movieRating = view.findViewById(R.id.textView2)
        movieDescription = view.findViewById(R.id.textView3)

        toolbar = view.findViewById(R.id.detail_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Movie Detail"

        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val movieId = args.movieId

        detailViewModel.getFullDetail(movieId)

        observeViewModel()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.detail_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {

                        R.id.action_fav -> {
                            val movie = detailViewModel.movieDetail.value
                            movie?.let {
                                val favoriteMovie = Movie(
                                    id = it.id,
                                    title = it.title,
                                    poster_path = it.poster_path,
                                    vote_average = it.vote_average,
                                    backdrop_path = it.backdrop_path,
                                    idRoom = it.id.toString(),
                                    vote_count = it.vote_count ?: 0,
                                    popularity = it.popularity ?: 0.0,
                                    overview = it.overview
                                )
                                favoriteViewModel.addFavMovie(favoriteMovie)
                                Toast.makeText(context, "Movie added to favorites", Toast.LENGTH_SHORT).show()
                            }
                            true
                        }

                        else -> false
                    }
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.CREATED
        )
    }

    private fun observeViewModel() {
        detailViewModel.movieDetail.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                progressBar.visibility = View.GONE
                //Glide.with(this).load(movie.poster_path).into(moviePoster)
                movieTitle.text = movie.title
                movieRating.text = movie.vote_average.toString()
                movieDescription.text = movie.overview

                val posterPath = movie.poster_path
                val posterUrl = "https://image.tmdb.org/t/p/w500$posterPath"

                Glide.with(this)
                    .load(posterUrl)
                    .into(moviePoster)
            }
        })

        detailViewModel.movieLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        detailViewModel.movieError.observe(viewLifecycleOwner, Observer { isError ->
            if (isError) {
                progressBar.visibility = View.GONE
                // Handle error case
            }
        })
    }

}