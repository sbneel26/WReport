package edu.weather.wreport.data.remote.api

//
import edu.weather.wreport.data.remote.model.ApiPost
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("interview-question-data/metoffice/Rainfall-England.json")
    fun getAllPost(): Single<List<ApiPost>>
}