package com.baitent.movies_imdb_kotlin_mvvm

import android.content.Context
import android.content.SharedPreferences
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.dowloadFromUrl(url:String?){

    val options = RequestOptions()
        .placeholder(placeholderProgressBar(context))
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)

}
fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 48f
        start()
    }
}

private lateinit var sharedPreferences: SharedPreferences

private var isDarkTheme = false

fun changeTheme() {

    val editor = sharedPreferences.edit()

    if (isDarkTheme) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        editor.putBoolean("nightMode", false)
        isDarkTheme = false
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        editor.putBoolean("nightMode", true)
        isDarkTheme = true
    }

    editor.apply()
    //  requireActivity().recreate()
}
