package edu.weather.wreport

import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import edu.weather.wreport.injection.component.ApplicationComponent
import edu.weather.wreport.injection.component.DaggerApplicationComponent

class WeatherApplication:Application(),HasSupportFragmentInjector {


    @Inject lateinit var fragmentDispatchingAndroidInjector :DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .application(this).build().inject(this)
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun supportFragmentInjector(): AndroidInjector<android.support.v4.app.Fragment> {
        return fragmentDispatchingAndroidInjector
    }

}
