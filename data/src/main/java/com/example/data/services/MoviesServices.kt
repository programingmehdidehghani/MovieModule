package com.example.data.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesServices {

    @GET("/3/movie/top_rated")
    suspend fun movies(@Query("page") page: Int): Response<>
}