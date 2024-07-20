package com.baitent.movies_imdb_kotlin_mvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.adapter.FavoritesAdapter
import com.baitent.movies_imdb_kotlin_mvvm.room.FavoritesViewModelFactory
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.FavoritesViewModel


class FavoritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private val favoriteViewModel: FavoritesViewModel by viewModels {
        FavoritesViewModelFactory(requireActivity().application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //tüm favorileri çekme
        favoriteViewModel.allFavMovies.observe(this) { movies ->
            recyclerView.adapter = FavoritesAdapter(movies)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewFav)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        toolbar = view.findViewById(R.id.toolbar2)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.title = "Favorites"
        return view
    }
}