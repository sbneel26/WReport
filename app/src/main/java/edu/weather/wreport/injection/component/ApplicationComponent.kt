package edu.weather.wreport.injection.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import edu.weather.wreport.WeatherApplication
import edu.weather.wreport.injection.builder.FragmentBuilder
import edu.weather.wreport.injection.module.ApplicationModule
import edu.weather.wreport.injection.scopes.PerApplication

@PerApplication
@Component(modules= arrayOf(ApplicationModule::class, FragmentBuilder::class, AndroidSupportInjectionModule::class))
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: WeatherApplication)
}