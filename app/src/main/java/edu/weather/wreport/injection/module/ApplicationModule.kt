package edu.weather.wreport.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import edu.weather.wreport.data.RepositoryImpl
import edu.weather.wreport.data.mapper.PostMapper
import edu.weather.wreport.data.remote.api.Api
import edu.weather.wreport.data.remote.api.ApiFactory
import edu.weather.wreport.domain.repository.Repository
import edu.weather.wreport.injection.scopes.PerApplication

@Module
open class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal  fun provideApi(): Api {
        return ApiFactory.makeApi()
    }

    @Provides
    @PerApplication
    internal fun provideMapper(): PostMapper {
        return PostMapper()
    }

    @Provides
    @PerApplication
    internal fun provideRepository(api: Api, mapper:PostMapper): Repository {
        return RepositoryImpl(api,mapper)
    }

}