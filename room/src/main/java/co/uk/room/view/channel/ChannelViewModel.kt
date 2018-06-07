package co.uk.room.view.channel

import android.arch.lifecycle.ViewModel
import co.uk.room.api.RoomApiService
import co.uk.room.error.ExceptionTransformers
import co.uk.room.model.Channel
import co.uk.room.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class ChannelViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: RoomApiService)
    : ViewModel() {
    fun getChannel(): Single<Channel> {
//        return getChannelFromRealm()
//                .flatMap {
//                    if (it.isNotEmpty()) {
//                        return@flatMap Single.just(it.first())
//                    } else return@flatMap getChannelFromApi()
//                }
        return getChannelFromApi()
    }

//    private fun getChannelFromRealm(): Single<List<Channel>> =
//            RealmHelper.findAll<Channel>().flatMap {
//                return@flatMap Single.just(it)
//            }

    private fun getChannelFromApi(): Single<Channel> {
        return youtubeApiService.getChannel()
                .compose(schedulerProvider.getSchedulersForSingle())
                .compose(exceptionTransformers.wrapRetrofitExceptionSingle())
                .flatMap {
                    //                    RealmHelper.copyOrUpdate(it)
                    return@flatMap Single.just(it)
                }
    }
}