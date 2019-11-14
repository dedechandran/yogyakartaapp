package com.yogyakarta.yogyakartaapp.data.repository.movie.remote

import android.util.Log
import com.example.androidjetpacksubmission.data.repositories.movie.remote.MovieService
import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieDetailResponse
import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieResponse
import com.yogyakarta.yogyakartaapp.data.repository.RepositoryCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DefaultMovieRemoteDataSource @Inject constructor(private val movieService: MovieService) :
    MovieRemoteDataSource {
    override fun loadMovies(callback: RepositoryCallback<MovieResponse>) {
        movieService.getAllMovies().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                print("MASUK")
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                print("MASUK")
                response.body()?.let { callback.onSuccess(it) }
            }

        })
    }

    override fun loadMovieDetail(movieId: Int, callback: RepositoryCallback<MovieDetailResponse>) {
        movieService.getMovie(movieId).enqueue(object : Callback<MovieDetailResponse>{
            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                callback.onFailure(t)
            }

            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                response.body()?.let { callback.onSuccess(it) }
            }

        })
    }

}