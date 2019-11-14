package com.yogyakarta.yogyakartaapp.data.repository.movie.remote

import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieDetailResponse
import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieResponse
import com.yogyakarta.yogyakartaapp.data.repository.RepositoryCallback


interface MovieRemoteDataSource {
    fun loadMovies(callback : RepositoryCallback<MovieResponse>)
    fun loadMovieDetail(movieId: Int, callback: RepositoryCallback<MovieDetailResponse>)
}