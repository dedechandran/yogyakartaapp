package com.example.androidjetpacksubmission.data.repositories.movie.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val data: List<MovieData>
) {
    data class MovieData(
        @SerializedName("id")
        var id: Int,

        @SerializedName("poster_path")
        var posterUrl: String,

        @SerializedName("title")
        var originalTitle: String,

        @SerializedName("release_date")
        var releaseDate: String,

        @SerializedName("overview")
        var overview: String
    )
}