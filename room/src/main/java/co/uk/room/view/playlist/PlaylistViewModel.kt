package co.uk.room.view.playlist

import android.arch.lifecycle.ViewModel
import co.uk.room.api.RoomApiService
import co.uk.room.error.ExceptionTransformers
import co.uk.room.model.Playlist
import co.uk.room.util.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class PlaylistViewModel @Inject constructor
(private val exceptionTransformers: ExceptionTransformers, private val schedulerProvider: SchedulerProvider, private val youtubeApiService: RoomApiService)
    : ViewModel(){
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