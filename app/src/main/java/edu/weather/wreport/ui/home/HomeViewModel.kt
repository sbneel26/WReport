package edu.weather.wreport.ui.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import edu.weather.wreport.domain.interactor.post.FetchAllPosts
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.util.Resource
import edu.weather.wreport.util.ResourceState
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val fetchAllPosts: FetchAllPosts) : ViewModel() {

    private val postsLiveData: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    fun fetchAllPost(): LiveData<Resource<List<Post>>> {
        fetchAllPosts.execute().subscribeOn(Schedulers.computation())
                .doOnSuccess({posts->postsLiveData.postValue(Resource(ResourceState.SUCCESS,
                        posts, null))})
                .doOnError({exception->postsLiveData.postValue(Resource(ResourceState.ERROR, null, exception.message))})
                .subscribe()
        return postsLiveData
    }

}