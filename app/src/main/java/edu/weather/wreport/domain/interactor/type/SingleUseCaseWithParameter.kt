package edu.weather.wreport.domain.interactor.type

import io.reactivex.Single

interface SingleUseCaseWithParameter<in P,Q,R> {
    fun execute(parameter: P, parameter2: Q): Single<R>
}