package com.yogyakarta.yogyakartaapp.data.mapper

import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieDetailResponse
import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieResponse
import com.yogyakarta.yogyakartaapp.domain.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() {
    fun transform(movieResponse: MovieResponse): List<Movie> {
        val movies = mutableListOf<Movie>()
        movieResponse.data.map {
            movies.add(
                Movie(
                    movieId = it.id,
                    movieReleaseDate = it.releaseDate,
                    movieTitle = it.originalTitle,
                    moviePoster = it.posterUrl,
                    movieOverview = it.overview
                )
            )
        }
        return movies
    }

    fun transform(movieDetailResponse: MovieDetailResponse): Movie =
        Movie(
            movieId = movieDetailResponse.id,
            movieOverview = movieDetailResponse.overview,
            moviePoster = movieDetailResponse.posterUrl,
            movieTitle = movieDetailResponse.originalTitle,
            movieReleaseDate = movieDetailResponse.releaseDate,
            movieLanguage = movieDetailResponse.originalLanguage,
            movieRevenue = movieDetailResponse.revenue.toString(),
            movieBudget = movieDetailResponse.budget.toString(),
            movieDuration = movieDetailResponse.runtime.toString(),
            movieGenres = movieDetailResponse.genres.map {
                it.name
            }

        )
}