package edu.weather.wreport.domain.interactor.type

import io.reactivex.Single

interface SingleUseCaseWithParameter<in P,R> {
    fun execute(parameter: P): Single<R>
}