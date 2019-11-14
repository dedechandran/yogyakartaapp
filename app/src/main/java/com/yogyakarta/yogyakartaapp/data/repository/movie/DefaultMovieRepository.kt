package com.yogyakarta.yogyakartaapp.data.repository.movie

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.yogyakarta.yogyakartaapp.data.repository.movie.remote.MovieRemoteDataSource
import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieDetailResponse
import com.example.androidjetpacksubmission.data.repositories.movie.remote.response.MovieResponse
import com.yogyakarta.yogyakartaapp.R
import com.yogyakarta.yogyakartaapp.data.mapper.MovieMapper
import com.yogyakarta.yogyakartaapp.data.repository.RepositoryCallback
import com.yogyakarta.yogyakartaapp.domain.Movie
import com.yogyakarta.yogyakartaapp.vo.Resource
import javax.inject.Inject

class DefaultMovieRepository @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val movieMapper: MovieMapper,
    private val context: Context
) : MovieRepository {
    override fun getAllMovies(): MutableLiveData<Resource<List<Movie>>> {
        val movies = MutableLiveData<Resource<List<Movie>>>()
        movies.postValue(Resource.loading())
        movieRemoteDataSource.loadMovies(object : RepositoryCallback<MovieResponse>{
            override fun onSuccess(data: MovieResponse) {
                val mappedMovies = movieMapper.transform(data)
                movies.postValue(Resource.success(mappedMovies))
            }

            override fun onFailure(t: Throwable) {
                movies.postValue(Resource.error(context.resources.getString(R.string.all_request_error_message)))
            }
        })
        return movies
    }

    override fun getMovie(movieId: Int): MutableLiveData<Resource<Movie>> {
        val movie = MutableLiveData<Resource<Movie>>()
        movie.postValue(Resource.loading())
        movieRemoteDataSource.loadMovieDetail(movieId,object : RepositoryCallback<MovieDetailResponse>{
            override fun onSuccess(data: MovieDetailResponse) {
                val mappedMovie = movieMapper.transform(data)
                movie.postValue(Resource.success(mappedMovie))
            }

            override fun onFailure(t: Throwable) {
                movie.postValue(Resource.error(context.resources.getString(R.string.all_request_error_message)))
            }
        })
        return movie
    }
}