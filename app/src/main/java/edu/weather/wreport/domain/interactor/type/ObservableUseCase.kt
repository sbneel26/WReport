package edu.weather.wreport.domain.interactor.type

import io.reactivex.Observable

interface ObservableUseCase<T> {
    fun execute(): Observable<T>
}