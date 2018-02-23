package co.uk.random.api

import co.uk.random.util.Keys.API_KEY
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {

    @GET("https://www.googleapis.com/youtube/v3/playlists?part=snippet&maxResults=50&channelId=UC_x5XG1OV2P6uZZ5FSM9Ttw&key=" + API_KEY)
    fun getChannelId(): Single<Response<Any>>

    @GET("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&key=" + API_KEY)
    fun getPlaylistId(@Query("playlistId") playlistId: String): Single<Response<Any>>

    @GET("https://www.googleapis.com/youtube/v3/videos?part=player&key=" + API_KEY)
    fun getVideoId(@Query("id") videoId: String): Single<Response<Any>>
}