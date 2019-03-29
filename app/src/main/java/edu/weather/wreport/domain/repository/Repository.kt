package edu.weather.wreport.domain.repository

import edu.weather.wreport.domain.model.Post
import io.reactivex.Single

interface Repository {
    fun getAllPost() : Single<List<Post>>
}