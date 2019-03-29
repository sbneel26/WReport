package edu.weather.wreport.injection.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import edu.rush.myrush.ui.home.HomeFragment
import edu.weather.wreport.injection.module.FragmentModule
import edu.weather.wreport.injection.scopes.PerActivity

@Module
abstract class FragmentBuilder {
    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun bindHomeFragment(): HomeFragment
}