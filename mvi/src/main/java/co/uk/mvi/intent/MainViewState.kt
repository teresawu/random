package co.uk.mvi.intent

import io.reactivex.subjects.PublishSubject

data class MainViewState<T>(var loadSubject: PublishSubject<T>)