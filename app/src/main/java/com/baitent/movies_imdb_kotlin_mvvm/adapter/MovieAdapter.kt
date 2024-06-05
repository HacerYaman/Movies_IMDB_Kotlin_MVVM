package com.baitent.movies_imdb_kotlin_mvvm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baitent.movies_imdb_kotlin_mvvm.R
import com.baitent.movies_imdb_kotlin_mvvm.model.MovieModel

class MovieAdapter(private val movieList:ArrayList<MovieModel>): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.findViewById(R.id.name_txt)
        val movieRate: TextView = view.findViewById(R.id.rate_txt)
        val moviePoster: ImageView = view.findViewById(R.id.poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item,parent,false)

        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieTitle.text = movie.movieTitle
        holder.movieRate.text = movie.rate
      //  holder.moviePoster.downloadFromUrl(movie.movieImg)
    }

    fun updateMovieList(newMovieList: List<MovieModel>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }


}