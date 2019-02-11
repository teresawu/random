package co.uk.youtube.view.channel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import co.uk.youtube.view.base.DisposingViewModel
import co.uk.youtube.view.channel.state.ChannelRepo
import co.uk.youtube.view.channel.state.ChannelState
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class ChannelViewModel @Inject constructor(var channelRepo: ChannelRepo) : DisposingViewModel() {
    val stateSubject: BehaviorSubject<ChannelState> = BehaviorSubject.create()

    val channelState = ChannelState()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getChannel() {
        add(channelRepo.getChannel()
                .doOnSubscribe {
                    stateSubject.onNext(channelState.copy(loading = true, loaded = false, error = false))
                }
                .map {
                    stateSubject.onNext(channelState.copy(loading = false, loaded = true, error = false, channel = it))
                }
                .onErrorReturn {
                    stateSubject.onNext(channelState.copy(loading = false, loaded = false, error = true))
                }
                .subscribe()
        )
    }
}