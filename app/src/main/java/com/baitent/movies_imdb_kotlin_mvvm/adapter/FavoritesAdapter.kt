package com.baitent.movies_imdb_kotlin_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie
import com.baitent.movies_imdb_kotlin_mvvm.view.FavoritesFragmentDirections
import com.bumptech.glide.Glide

class FavoritesAdapter(private val favoritesList: List<Movie>) :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.poster)
        val nameTxt: TextView = view.findViewById(R.id.name_txt)
        val rateTxt: TextView = view.findViewById(R.id.rate_txt)

        fun onPress(fav_movie: Movie) {
            itemView.setOnClickListener {
                val action =
                    FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(fav_movie.id)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return FavoritesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return favoritesList.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val fav_movie = favoritesList[position]

        holder.nameTxt.text = fav_movie.title
        holder.rateTxt.text = fav_movie.vote_average.toString()

        val posterPath = fav_movie.poster_path
        val posterUrl = "https://image.tmdb.org/t/p/w500$posterPath"

        Glide.with(holder.itemView.context)
            .load(posterUrl)
            .into(holder.poster)

        holder.onPress(fav_movie)

    }
}