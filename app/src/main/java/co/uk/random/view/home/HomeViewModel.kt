package co.uk.random.view.home

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.model.Channel
import co.uk.random.model.Playlist
import co.uk.random.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class HomeViewModel @Inject constructor
(private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService) {

    private val exceptionTransformers by lazy { ExceptionTransformers() }

    fun getChannel(): Single<Channel> {
        return youtubeApiService.getChannel()
                .compose(exceptionTransformers.wrapRetrofitExceptionSingle())
                .flatMap { return@flatMap Single.just(it) }
    }

    fun getPlaylist(playlistId: String): Single<Playlist> {
        return youtubeApiService.getPlaylist(playlistId)
                .compose(exceptionTransformers.wrapRetrofitExceptionSingle())
                .flatMap { return@flatMap Single.just(it) }
    }
}