package com.yogyakarta.yogyakartaapp.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.androidjetpacksubmission.utils.DataHelper
import com.yogyakarta.yogyakartaapp.data.repository.movie.MovieRepository
import com.yogyakarta.yogyakartaapp.domain.Movie
import com.yogyakarta.yogyakartaapp.vo.Resource
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    private var movies = MutableLiveData<Resource<List<Movie>>>()
    private var movie = MutableLiveData<Resource<Movie>>()

    private fun movieTransformation(resource: Resource<Movie>): Resource<Movie> {
        resource.data?.apply {
            movieLanguage = DataHelper.convertLanguageCode(movieLanguage!!)
            movieBudget = DataHelper.convertNominalToDollar(movieBudget?.toLong()!!)
            movieRevenue = DataHelper.convertNominalToDollar(movieRevenue?.toLong()!!)
            movieDuration = DataHelper.convertDurationToHoursAndMinute(movieDuration?.toInt()!!)
        }
        return Resource(
            resource.status,
            resource.data,
            resource.message
        )
    }

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        if (movies.value != null) {
            return movies
        }
        movies = movieRepository.getAllMovies()
        return movies
    }

    fun getMovieDetail(movieId: Int): LiveData<Resource<Movie>> {
        if (movie.value != null) {
            return movie
        }
        movie = movieRepository.getMovie(movieId)
        return Transformations.map(movie, ::movieTransformation)
    }
}