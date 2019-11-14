package com.yogyakarta.yogyakartaapp.di.modules

import com.yogyakarta.yogyakartaapp.data.repository.movie.MovieRepository
import com.yogyakarta.yogyakartaapp.data.repository.movie.DefaultMovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMovieRepository(defaultMovieRepository: DefaultMovieRepository) : MovieRepository = defaultMovieRepository
}