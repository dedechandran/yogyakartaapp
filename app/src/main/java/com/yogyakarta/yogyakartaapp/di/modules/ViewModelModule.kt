package com.yogyakarta.yogyakartaapp.di.modules

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yogyakarta.yogyakartaapp.data.repository.movie.MovieRepository
import com.yogyakarta.yogyakartaapp.di.ViewModelKey
import com.yogyakarta.yogyakartaapp.ui.account.AccountViewModel
import com.yogyakarta.yogyakartaapp.ui.movie.MovieViewModel
import com.yogyakarta.yogyakartaapp.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
object ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun provideMovieViewModel(movieRepository: MovieRepository) : ViewModel = MovieViewModel(movieRepository)

    @Provides
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    fun provideAccountViewModel(context: Context) : ViewModel =
        AccountViewModel(context)

    @Provides
    @Singleton
    fun provideViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory = viewModelFactory
}