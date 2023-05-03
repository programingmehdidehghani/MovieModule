package com.example.movieapi.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.movieapi.R
import com.example.movieapi.databinding.ActivityDetailMoviesBinding
import com.example.movieapi.di.IMAGE_URL

class MoviesDetailActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        ActivityDetailMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        intent.apply {
            supportActionBar?.apply {
                title = getStringExtra(BUNDLE_TITLE)
                setDisplayHomeAsUpEnabled(true)
            }
            Glide.with(this@MoviesDetailActivity).load(IMAGE_URL + getStringExtra(BUNDLE_PATH))
                .into(viewBinding.imageViewPoster)
            viewBinding.textViewOverView.text = getStringExtra(BUNDLE_OVERVIEW)
            viewBinding.textViewPopularity.text = getStringExtra(BUNDLE_POPULARITY)
            viewBinding.textViewAverage.text = getStringExtra(BUNDLE_AVERAGE)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val BUNDLE_TITLE = "title"
        const val BUNDLE_PATH = "path"
        const val BUNDLE_OVERVIEW = "overview"
        const val BUNDLE_POPULARITY = "popularity"
        const val BUNDLE_AVERAGE = "average"
    }
}