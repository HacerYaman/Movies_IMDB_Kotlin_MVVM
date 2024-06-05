package com.baitent.movies_imdb_kotlin_mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity
data class MovieModel(

    //GET/imdbSearchById
    // apikey 4br51f6CVUfpaOhEE6Dv29:5Qd5S3dILLln24Wq4XfUju

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