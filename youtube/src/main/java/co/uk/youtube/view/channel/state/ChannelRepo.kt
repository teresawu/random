package co.uk.youtube.view.channel.state

import co.uk.youtube.api.YoutubeApiService
import co.uk.youtube.error.ExceptionTransformers
import co.uk.youtube.model.Channel
import co.uk.youtube.util.RealmHelper
import co.uk.youtube.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class ChannelRepo @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService) {

    fun getChannel(): Single<Channel> {
        return getChannelFromRealm()
                .flatMap {
                    if (it.isNotEmpty()) {
                        return@flatMap Single.just(it.first())
                    } else return@flatMap getChannelFromApi()
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