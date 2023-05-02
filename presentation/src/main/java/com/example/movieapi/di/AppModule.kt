package com.example.movieapi.di

import com.example.data.repository.MoviesRepositoryImpl
import com.example.data.services.MoviesServices
import com.example.domain.interactor.GetMoviesInteractor
import com.example.domain.repositories.MoviesRepository
import org.koin.dsl.module


val repositoryModules = module {
    single {

    }
}


val interactorModules = module {
    factory(name = GET_MOVIES_INTERACTOR) {
        GetMoviesInteractor(moviesRepository = get())
    }
}


private fun provideMoviesRepository(moviesServices: MoviesServices): MoviesRepository =
    MoviesRepositoryImpl(moviesServices = moviesServices)

const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
private const val BASE_URL = "https://api.themoviedb.org/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_MOVIES_INTERACTOR = "getMoviesInteractor"