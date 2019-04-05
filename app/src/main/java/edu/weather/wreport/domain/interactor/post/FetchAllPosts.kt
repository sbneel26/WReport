package edu.weather.wreport.domain.interactor.post

import edu.weather.wreport.domain.interactor.type.SingleUseCaseWithParameter
import edu.weather.wreport.domain.model.Post
import edu.weather.wreport.domain.repository.Repository
import io.reactivex.Single
import javax.inject.Inject

class FetchAllPosts@Inject constructor(private val repository: Repository) : SingleUseCaseWithParameter<String, String, ArrayList<Post>> {
    override fun execute(parameter: String, parameter2: String): Single<ArrayList<Post>> {
       return repository.getAllPost(parameter, parameter2)
    }
}