package com.example.movieapi.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.interactor.GetMoviesInteractor
import com.example.domain.model.MoviesData
import com.example.movieapi.common.BaseViewModel
import com.example.movieapi.entities.DataHolder
import com.example.movieapi.extension.launchViewModelScope
import com.example.movieapi.extension.loadingData
import com.example.movieapi.extension.responseData

class MoviesViewModel (
    private val getMoviesInteractor: GetMoviesInteractor
) : BaseViewModel(){

    private val _moviesLiveData = MutableLiveData<DataHolder<MoviesData?>>()
    val moviesLiveData: LiveData<DataHolder<MoviesData?>>
        get() = _moviesLiveData

    fun getMovies(
        page: Int
    ) = launchViewModelScope {
        _moviesLiveData
            .loadingData()
            .responseData(
                getMoviesInteractor.getMovies(
                    page
                )
            )
    }
}