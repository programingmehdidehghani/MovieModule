package com.example.movieapi.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapi.R
import com.example.movieapi.databinding.ActivityDetailMoviesBinding
import com.example.movieapi.databinding.ActivityMoviesBinding

class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var listAdapter: MoviesListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private var loadingPage = 1

    private val viewBinding by lazy {
        ActivityMoviesBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        listAdapter = MoviesListAdapter()
        layoutManager = GridLayoutManager(this,2)
        viewBinding.recyclerViewMovies.layoutManager = layoutManager
        viewBinding.recyclerViewMovies.adapter = listAdapter
        moviesViewModel.getMovies(loadingPage)

        viewBinding.buttonLoadMore.setOnClickListener {
            loadingPage++
            moviesViewModel.getMovies(loadingPage)
        }

    }
}