package co.uk.arch.view.playlist

import android.arch.lifecycle.ViewModel
import co.uk.arch.api.ArchApiService
import co.uk.arch.error.ExceptionTransformers
import co.uk.arch.model.Playlist
import co.uk.arch.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class PlaylistViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: ArchApiService)
    : ViewModel() {
    fun getPlaylist(playlistId: String): Single<Playlist> {
//        return getPlaylistFromRealm()
//                .flatMap {
//                    if (it.isNotEmpty()) return@flatMap Single.just(it.first())
//                    else return@flatMap getPlaylistFromApi(playlistId)
//                }
        return getPlaylistFromApi(playlistId)
    }


    private fun getPlaylistFromApi(playlistId: String): Single<Playlist> {
        return youtubeApiService.getPlaylist(playlistId)
                .compose(schedulerProvider.getSchedulersForSingle())
                .compose(exceptionTransformers.wrapRetrofitExceptionSingle())
                .flatMap {
                    //                    RealmHelper.copyOrUpdate(it)
                    return@flatMap Single.just(it)
                }
    }

//    private fun getPlaylistFromRealm(): Single<List<Playlist>> =
//            RealmHelper.findAll<Playlist>().flatMap {
//                return@flatMap Single.just(it)
//            }
}