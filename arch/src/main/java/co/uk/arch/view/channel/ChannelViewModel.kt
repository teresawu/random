package co.uk.arch.view.channel

import android.arch.lifecycle.ViewModel
import co.uk.arch.api.ArchApiService
import co.uk.arch.error.ExceptionTransformers
import co.uk.arch.model.Channel
import co.uk.arch.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class ChannelViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: ArchApiService)
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