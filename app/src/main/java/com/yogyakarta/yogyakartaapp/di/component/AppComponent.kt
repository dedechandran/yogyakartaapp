package com.yogyakarta.yogyakartaapp.di.component

import com.yogyakarta.yogyakartaapp.AppCore
import com.yogyakarta.yogyakartaapp.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class),
        (AppModule::class),
        (NetworkModule::class),
        (ViewModelModule::class),
        (ActivitiesModule::class),
        (RepositoryModule::class),
        (ApiModule::class),
        (DataSourceModule::class)]
)
interface AppComponent : AndroidInjector<AppCore> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<AppCore>
}