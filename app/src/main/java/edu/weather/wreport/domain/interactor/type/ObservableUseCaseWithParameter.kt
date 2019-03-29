package edu.weather.wreport.domain.interactor.type

import io.reactivex.Observable

interface ObservableUseCaseWithParameter<in P,R> {
    fun execute(parameter: P): Observable<R>
}