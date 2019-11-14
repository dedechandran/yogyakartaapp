package com.example.androidjetpacksubmission.data.repositories.movie.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val posterUrl: String,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("title")
    var originalTitle: String,

    @SerializedName("release_date")
    var releaseDate: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("revenue")
    val revenue: Long,

    @SerializedName("budget")
    val budget: Long,

    @SerializedName("runtime")
    val runtime: Int

) {
    data class Genre(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String
    )
}