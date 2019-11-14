package com.yogyakarta.yogyakartaapp.di.modules

import android.content.Context
import com.yogyakarta.yogyakartaapp.AppCore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object AppModule {
    @Provides
    @Singleton
    fun provideAppContext(appCore: AppCore) : Context = appCore
}