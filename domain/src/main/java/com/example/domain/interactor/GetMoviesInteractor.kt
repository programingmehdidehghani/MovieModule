package com.example.domain.interactor

import com.example.domain.model.MoviesData
import com.example.domain.repositories.MoviesRepository
import retrofit2.Response

class GetMoviesInteractor(
    private val moviesRepository: MoviesRepository
) {

    suspend fun getMovies(
        page: Int
    ): Response<MoviesData>{
        return moviesRepository.getMovies(page)
    }
}