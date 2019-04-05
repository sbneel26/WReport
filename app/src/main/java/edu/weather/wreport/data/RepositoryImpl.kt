package edu.weather.wreport.data

import edu.weather.wreport.data.mapper.PostMapper
import edu.weather.wreport.data.remote.api.Api
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.domain.repository.Repository
import io.reactivex.Single

class RepositoryImpl(private val api: Api, private val mapper: PostMapper) : Repository {

    override fun getAllPost(climate: String, location: String): Single<ArrayList<Post>> {
        var url = "interview-question-data/metoffice/"
        val matchingClimate = when (climate) {
            "Min Temperature" -> "Tmin"
            "Max Temperature" -> "Tmax"
            else -> {
                "Rainfall"
            }
        }
        url = url.plus(matchingClimate).plus("-").plus(location).plus(".json")
        return api.getAllPost(url).flatMap(mapper)
    }
}