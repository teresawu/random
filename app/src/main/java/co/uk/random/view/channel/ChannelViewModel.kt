package co.uk.random.view.channel

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.model.Channel
import co.uk.random.util.RealmHelper
import co.uk.random.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class ChannelViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService) {
    fun getChannel(): Single<Channel> {
        return getChannelFromRealm()
                .flatMap {
                    if (it.isNotEmpty()) return@flatMap Single.just(it.first())
                    else return@flatMap getChannelFromApi()
                }
    }

    private fun getChannelFromRealm(): Single<List<Channel>> =
            RealmHelper.findAll<Channel>().flatMap {
                return@flatMap Single.just(it)
            }

    private fun getChannelFromApi(): Single<Channel> {
        return youtubeApiService.getChannel()
                .compose(schedulerProvider.getSchedulersForSingle())
                .compose(exceptionTransformers.wrapRetrofitExceptionSingle())
                .flatMap {
                    RealmHelper.copyOrUpdate(it)
                    return@flatMap Single.just(it)
                }
    }
}