package com.yogyakarta.yogyakartaapp.di.modules

import com.yogyakarta.yogyakartaapp.data.repository.movie.remote.DefaultMovieRemoteDataSource
import com.yogyakarta.yogyakartaapp.data.repository.movie.remote.MovieRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataSourceModule {
    @Provides
    @Singleton
    fun provideMovieRemoteDataSource(defaultMovieRemoteDataSource: DefaultMovieRemoteDataSource) : MovieRemoteDataSource = defaultMovieRemoteDataSource
}