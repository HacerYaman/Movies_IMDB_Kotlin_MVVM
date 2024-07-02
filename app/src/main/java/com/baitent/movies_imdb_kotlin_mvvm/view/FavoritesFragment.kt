package com.baitent.movies_imdb_kotlin_mvvm.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.adapter.FavoritesAdapter
import com.baitent.movies_imdb_kotlin_mvvm.viewmodel.FavoritesViewModel


class FavoritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var favoritesAdapter: FavoritesAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var sharedPreferences: SharedPreferences
    private val favoritesViewModel: FavoritesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


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

        favoritesViewModel.favorites.observe(viewLifecycleOwner, Observer { favorites ->
            favorites?.let {
                favoritesAdapter = FavoritesAdapter(it)
                recyclerView.adapter = favoritesAdapter
            }
        })

        return view
    }



}