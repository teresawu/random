package co.uk.random.view.playlist

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.model.Playlist
import co.uk.random.util.RealmHelper
import co.uk.random.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class PlaylistViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: YoutubeApiService) {
    fun getPlaylist(playlistId: String): Single<Playlist> {
        return getPlaylistFromRealm()
                .flatMap {
                    if (it.isNotEmpty()) return@flatMap Single.just(it.first())
                    else return@flatMap getPlaylistFromApi(playlistId)
                }
    }


    private fun getPlaylistFromApi(playlistId: String): Single<Playlist> {
        return youtubeApiService.getPlaylist(playlistId)
                .compose(schedulerProvider.getSchedulersForSingle())
                .compose(exceptionTransformers.wrapRetrofitExceptionSingle())
                .flatMap {
                    RealmHelper.copyOrUpdate(it)
                    return@flatMap Single.just(it)
                }
    }

    private fun getPlaylistFromRealm(): Single<List<Playlist>> =
            RealmHelper.findAll<Playlist>().flatMap {
                return@flatMap Single.just(it)
            }
}