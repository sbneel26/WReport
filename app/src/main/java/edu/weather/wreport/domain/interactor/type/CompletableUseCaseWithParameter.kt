package edu.weather.wreport.domain.interactor.type

import io.reactivex.Completable

interface CompletableUseCaseWithParameter<in P> {
    fun execute(parameter: P): Completable
}