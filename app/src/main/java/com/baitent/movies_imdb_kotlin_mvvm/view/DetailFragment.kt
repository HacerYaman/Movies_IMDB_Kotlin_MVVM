package com.baitent.movies_imdb_kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.DetailViewModel
import com.bumptech.glide.Glide


class DetailFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var moviePoster: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieRating: TextView
    private lateinit var movieDescription: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        progressBar = view.findViewById(R.id.progressBar2)
        moviePoster = view.findViewById(R.id.movie_poster)
        movieTitle = view.findViewById(R.id.textView)
        movieRating = view.findViewById(R.id.textView2)
        movieDescription = view.findViewById(R.id.textView3)

        val args = DetailFragmentArgs.fromBundle(requireArguments())
        val movieId = args.movieId

        detailViewModel.getFullDetail(movieId.toString())

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        detailViewModel.movieDetail.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                progressBar.visibility = View.GONE
                Glide.with(this).load(movie.poster_path).into(moviePoster)
                movieTitle.text = movie.title
                movieRating.text = movie.vote_average.toString()
                movieDescription.text = movie.overview
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