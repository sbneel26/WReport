package edu.weather.wreport.injection.module

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import edu.weather.wreport.domain.interactor.post.FetchAllPosts
import edu.weather.wreport.ui.home.HomeViewModel
import edu.weather.wreport.util.ViewModelFactoryUtil

@Module
open class FragmentModule{

    @Provides
    internal fun provideHomeViewModel(fetchAllPosts: FetchAllPosts): ViewModelProvider.Factory {
        return ViewModelFactoryUtil().createFor(
                HomeViewModel(fetchAllPosts))
    }
}