package edu.weather.wreport.data.remote.api

//
import edu.weather.wreport.data.remote.model.ApiPost
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET("")
    fun getAllPost(@Url url: String): Single<ArrayList<ApiPost>>
}