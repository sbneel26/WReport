package edu.weather.wreport.data

import edu.weather.wreport.data.mapper.PostMapper
import edu.weather.wreport.data.remote.api.Api
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.domain.repository.Repository
import io.reactivex.Single

class RepositoryImpl(private val api: Api, private val mapper: PostMapper) : Repository {

    override fun getAllPost(): Single<List<Post>> {
           return api.getAllPost().flatMap(mapper)
    }
}