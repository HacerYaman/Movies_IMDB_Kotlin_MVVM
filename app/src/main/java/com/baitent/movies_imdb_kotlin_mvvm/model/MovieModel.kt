package com.baitent.movies_imdb_kotlin_mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "moviesResponse")
data class MovieResponse(
    @PrimaryKey val id: String,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey val idRoom: String,
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val popularity: Double?,
)