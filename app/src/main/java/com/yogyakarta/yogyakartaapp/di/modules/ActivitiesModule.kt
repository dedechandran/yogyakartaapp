package com.yogyakarta.yogyakartaapp.di.modules

import com.yogyakarta.yogyakartaapp.ui.*
import com.yogyakarta.yogyakartaapp.ui.account.AccountActivity
import com.yogyakarta.yogyakartaapp.ui.movie.MovieActivity
import com.yogyakarta.yogyakartaapp.ui.movie.MovieDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun provideSplashActivity() : SplashActivity

    @ContributesAndroidInjector
    abstract fun provideRegisterActivity() : RegisterActivity

    @ContributesAndroidInjector
    abstract fun provideLoginActivity() : LoginActivity

    @ContributesAndroidInjector
    abstract fun provideArticleActivity() : MovieActivity

    @ContributesAndroidInjector
    abstract fun provideArticleDetailActivity() : MovieDetailActivity

    @ContributesAndroidInjector
    abstract fun provideAccountActivity() : AccountActivity
}