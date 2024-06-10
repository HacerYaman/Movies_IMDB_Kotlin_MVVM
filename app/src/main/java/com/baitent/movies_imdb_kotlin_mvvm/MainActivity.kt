package com.baitent.movies_imdb_kotlin_mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.baitent.movies_imdb_kotlin_mvvm.sercives.api_acces_token_long
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {


    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        CoroutineScope(Dispatchers.IO).launch {
            val response = makeNetworkRequest()
            if (response != null) {
                CoroutineScope(Dispatchers.Main).launch {
                    handleResponse(response)
                }
            }
        }
    }

    private fun makeNetworkRequest(): Response? {
        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/movie/popular?language=en-US&page=1")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", api_acces_token_long)
            .build()

        return try {
            client.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun handleResponse(response: Response) {
        if (response.isSuccessful) {
            // Do something with the response
           println("başarılı")
            // Update UI or handle data as needed
        } else {
            // Handle the error
        }
    }
}