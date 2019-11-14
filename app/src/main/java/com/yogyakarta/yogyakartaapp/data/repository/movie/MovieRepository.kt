package com.yogyakarta.yogyakartaapp.data.repository.movie

import androidx.lifecycle.MutableLiveData
import com.yogyakarta.yogyakartaapp.domain.Movie
import com.yogyakarta.yogyakartaapp.vo.Resource

interface MovieRepository {
    fun getAllMovies() : MutableLiveData<Resource<List<Movie>>>
    fun getMovie(movieId : Int) : MutableLiveData<Resource<Movie>>
}