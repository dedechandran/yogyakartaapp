package com.yogyakarta.yogyakartaapp.domain

data class Movie(
    var movieId: Int,
    var movieTitle: String,
    var moviePoster: String,
    var movieOverview: String,
    var movieReleaseDate: String,
    var movieDuration: String? = null,
    var movieGenres: List<String>? = null,
    var movieLanguage: String? = null,
    var movieBudget: String? = null,
    var movieRevenue: String? = null
)