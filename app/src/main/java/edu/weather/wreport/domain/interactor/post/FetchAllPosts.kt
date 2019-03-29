package edu.weather.wreport.domain.interactor.post

import edu.weather.wreport.domain.interactor.type.SingleUseCase
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class FetchAllPosts@Inject constructor(private val repository: Repository) : SingleUseCase<List<Post>> {

    override fun execute(): Single<List<Post>> {
       return repository.getAllPost()
    }
}