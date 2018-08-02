package co.uk.youtube

import co.uk.youtube.setup.ModelProvider
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Response

@Suppress("IllegalIdentifier")
class ServiceApiUnitTest internal constructor() {
    private val youtubeService by lazy { ModelProvider.youtubeService }

    @Before
    fun beforeClassSetup() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun `test serviceApi`() {
        //channel
        val channelObserver = TestObserver<Response<co.uk.youtube.model.Channel>>()
        youtubeService.getChannel().subscribe(channelObserver)
        channelObserver.assertComplete()

        //playlist
        val playlistObserver = TestObserver<Response<co.uk.youtube.model.Playlist>>()
        youtubeService.getPlaylist("PLOU2XLYxmsIKNoP4CHz0m8oY8doW5fRSa").subscribe(playlistObserver)
        playlistObserver.assertNoErrors()

        //video
        val videoObserver = TestObserver<Response<co.uk.youtube.model.Video>>()
        youtubeService.getVideo("XdZXpFBvTP8").subscribe(videoObserver)
        videoObserver.assertSubscribed()
    }
}