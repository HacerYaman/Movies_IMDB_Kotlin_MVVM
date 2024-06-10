package com.baitent.movies_imdb_kotlin_mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class MovieModel(


    @ColumnInfo("Title")
    @SerializedName("Title")
    var movieTitle: String?,

    @ColumnInfo("Poster")
    @SerializedName("Poster")
    var movieImg: String?,

    @ColumnInfo("Type")
    @SerializedName("Type")
    var type: String?,

    @ColumnInfo("imdbRating")
    @SerializedName("imdbRating")
    var rate: String?,

    @ColumnInfo("imdbID")
    @SerializedName("imdbID")
    var id: String?,

    @ColumnInfo("Plot")
    @SerializedName("Plot")
    var plot: String?
    )


data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val backdrop_path: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int,
    val popularity: Double
)
