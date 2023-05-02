package com.example.data.repository

import com.example.data.services.MoviesServices
import com.example.domain.model.MoviesData
import com.example.domain.repositories.MoviesRepository
import retrofit2.Response

class MoviesRepositoryImpl constructor(private val  moviesServices: MoviesServices)
    : MoviesRepository{
    override suspend fun getMovies(page: Int): Response<MoviesData> {
        return moviesServices.movies(page)
    }
}