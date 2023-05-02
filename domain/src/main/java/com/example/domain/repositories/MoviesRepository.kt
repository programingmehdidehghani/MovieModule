package com.example.domain.repositories

import com.example.domain.model.MoviesData
import retrofit2.Response

interface MoviesRepository {

    suspend fun getMovies(
        page : Int
    ) : Response<MoviesData>
}