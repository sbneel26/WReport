package edu.weather.wreport.data.mapper

import edu.weather.wreport.data.remote.model.ApiPost
import edu.weather.wreport.domain.model.Post
import io.reactivex.Single
import io.reactivex.functions.Function

class PostMapper : Function<List<ApiPost>, Single<List<Post>>> {
    override fun apply(t: List<ApiPost>): Single<List<Post>> {
        val postList = t.map { Post(it.tempId, it.year, it.month) }
        return Single.just(postList)
    }
}