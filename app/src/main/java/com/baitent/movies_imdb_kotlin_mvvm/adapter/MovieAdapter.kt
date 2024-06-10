package com.baitent.movies_imdb_kotlin_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.model.Movie

class MovieAdapter(private val movieList: List<Movie>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val poster: ImageView = view.findViewById(R.id.poster)
        val nameTxt: TextView = view.findViewById(R.id.name_txt)
        val rateTxt: TextView = view.findViewById(R.id.rate_txt)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.nameTxt.text = movie.title
        holder.rateTxt.text = movie.vote_average.toString()
    }
}