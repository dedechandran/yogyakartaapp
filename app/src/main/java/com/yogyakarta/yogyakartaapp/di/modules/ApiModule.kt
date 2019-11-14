package com.yogyakarta.yogyakartaapp.di.modules

import com.example.androidjetpacksubmission.data.repositories.movie.remote.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ApiModule {
    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit) : MovieService = retrofit.create(MovieService::class.java)
}