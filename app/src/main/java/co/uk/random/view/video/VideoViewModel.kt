package co.uk.random.view.video

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.model.Item
import co.uk.random.model.Video
import co.uk.random.util.RealmHelper
import co.uk.random.util.SchedulerProvider
import co.uk.random.util.Util
import io.reactivex.Single
import javax.inject.Inject

class VideoViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService) {

    fun getVideo(item: Item): Single<Video> {
        return getVideoFromRealm()
                .flatMap {
                    if (it.isNotEmpty()) return@flatMap Single.just(it.first())
                    else return@flatMap getVideoFromApi(item)
                }
    }

    fun getVideoFromApi(item: Item): Single<Video> {
        val video = Util.getMockVideo(item)
        RealmHelper.copyOrUpdate(video)
        return Single.just(video)
    }

    private fun getVideoFromRealm(): Single<List<Video>> =
            RealmHelper.findAll<Video>().flatMap {
                return@flatMap Single.just(it)
            }
}