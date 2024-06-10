package com.baitent.movies_imdb_kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.adapter.MovieAdapter
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.MovieListViewModel

class MoviesFragment : Fragment() {

    private val movieListViewModel: MovieListViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ViewModel'den veriyi almak için getData fonksiyonunu çağır
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

        // Verileri gözlemleyin ve RecyclerView'a aktarın
        movieListViewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                movieAdapter = MovieAdapter(it)
                recyclerView.adapter = movieAdapter
            }
        })

        // Yükleme durumunu gözlemleyin
        movieListViewModel.moviesLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        // Hata durumunu gözlemleyin
        movieListViewModel.moviesError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                // Hata durumunu yönetin
            }
        })

        return view
    }
}
