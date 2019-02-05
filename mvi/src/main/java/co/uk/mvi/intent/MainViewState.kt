package co.uk.mvi.intent

import io.reactivex.subjects.PublishSubject

data class MainViewState<T>(var loadSubject: PublishSubject<T>, var errorSubject: PublishSubject<T>, var loadedSubject: PublishSubject<T>, var buttonSubject: PublishSubject<T>)