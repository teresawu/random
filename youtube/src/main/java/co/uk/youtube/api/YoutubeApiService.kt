package co.uk.youtube.api

import co.uk.youtube.util.Keys.API_KEY
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {

    @GET("https://www.googleapis.com/youtube/v3/playlists?part=snippet&maxResults=50&channelId=UC_x5XG1OV2P6uZZ5FSM9Ttw&key=" + API_KEY)
    fun getChannel(): Single<Response<co.uk.youtube.model.Channel>>

    //playlistId = "items" -> "id (PLOU2XLYxmsIKNoP4CHz0m8oY8doW5fRSa)"
    @GET("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&key=" + API_KEY)
    fun getPlaylist(@Query("playlistId") playlistId: String): Single<Response<co.uk.youtube.model.Playlist>>

//    @GET("https://www.googleapis.com/youtube/v3/videos?part=player&key=" + API_KEY)
//    fun getVideo(@Query("id") videoId: String): Single<Response<Video>>
}