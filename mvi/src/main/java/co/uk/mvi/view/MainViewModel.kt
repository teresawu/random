package co.uk.mvi.view

import co.uk.mvi.intent.MainViewState
import co.uk.mvi.models.TeamRepository
import io.reactivex.subjects.PublishSubject

class MainViewModel {
    private val teamRepository = TeamRepository()
    private lateinit var mainViewState: MainViewState<Boolean>
    val loadSubject by lazy { PublishSubject.create<Boolean>() }
    val loadedSubject by lazy { PublishSubject.create<Boolean>() }
    val errorSubject by lazy { PublishSubject.create<Boolean>() }
    val buttonSubject by lazy { PublishSubject.create<Boolean>() }

    fun viewStateReducer(): MainViewState<Boolean> {
        if (teamRepository.fetchTeamList() == null) {
            loadSubject.onNext(true)
            loadedSubject.onNext(false)
            errorSubject.onNext(false)
        }

        if (teamRepository.fetchTeamList() != null) {
            loadSubject.onNext(false)
            loadedSubject.onNext(true)
            errorSubject.onNext(false)
        }

        if (teamRepository.fetchTeamList().size >= 100)
            buttonSubject.onNext(true)
        else buttonSubject.onNext(false)
        return mainViewState
    }
}