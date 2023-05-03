package com.example.movieapi.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.model.MoviesItemData
import com.example.movieapi.R
import com.example.movieapi.databinding.ActivityDetailMoviesBinding
import com.example.movieapi.databinding.ActivityMoviesBinding
import com.example.movieapi.extension.observeResponse

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
        layoutManager = GridLayoutManager(this, 2)
        viewBinding.recyclerViewMovies.layoutManager = layoutManager
        viewBinding.recyclerViewMovies.adapter = listAdapter
        moviesViewModel.getMovies(loadingPage)

        viewBinding.buttonLoadMore.setOnClickListener {
            loadingPage++
            moviesViewModel.getMovies(loadingPage)
        }

        moviesViewModel.moviesLiveData.observeResponse(
            owner = this,
            loading = { loading ->
                 Toast.makeText(this, loading , Toast.LENGTH_SHORT).show()
            },
            fail = { error ->
                 Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
            },
            success = { moviesData ->
                  moviesData?.let {
                      listAdapter.updateList(moviesData.results as MutableList<MoviesItemData>)
                  }
            }
        )
        viewBinding.searchViewMovies.setOnQueryTextListener( object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty() || newText.length > 2)
                    listAdapter.filter.filter(newText)
                return false
            }
        })

    }
}