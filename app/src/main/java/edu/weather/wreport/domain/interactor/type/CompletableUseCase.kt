package edu.weather.wreport.domain.interactor.type

import io.reactivex.Completable

interface CompletableUseCase {
    fun execute(): Completable
}