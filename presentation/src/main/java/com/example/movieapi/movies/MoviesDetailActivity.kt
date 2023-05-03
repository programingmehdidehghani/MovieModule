package com.example.movieapi.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapi.R
import com.example.movieapi.databinding.ActivityDetailMoviesBinding

class MoviesDetailActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityDetailMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

    companion object {
        const val BUNDLE_TITLE = "title"
        const val BUNDLE_PATH = "path"
        const val BUNDLE_OVERVIEW = "overview"
        const val BUNDLE_POPULARITY = "popularity"
        const val BUNDLE_AVERAGE = "average"
    }
}