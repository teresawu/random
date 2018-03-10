package co.uk.random.view.video

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.model.Playlist
import co.uk.random.model.Video
import co.uk.random.util.RealmHelper
import co.uk.random.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class VideoViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService) {

    fun getVideo(videoId: String): Single<Video> {
        return getVideoFromRealm()
                .flatMap {
                    if (it.isNotEmpty()) return@flatMap Single.just(it.first())
                    else return@flatMap getVideoFromApi(videoId)
                }
    }


    fun getVideoFromApi(videoId: String): Single<Video> {
        return youtubeApiService.getVideo(videoId)
                .compose(schedulerProvider.getSchedulersForSingle())
                .compose(exceptionTransformers.wrapRetrofitExceptionSingle())
                .flatMap {
                    RealmHelper.copyOrUpdate(it)
                    return@flatMap Single.just(it)
                }
    }

    private fun getVideoFromRealm(): Single<List<Video>> =
            RealmHelper.findAll<Video>().flatMap {
                return@flatMap Single.just(it)
            }
}