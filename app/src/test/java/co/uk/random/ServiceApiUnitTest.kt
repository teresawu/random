package co.uk.random

import co.uk.random.model.Channel
import co.uk.random.model.Playlist
import co.uk.random.model.Video
import co.uk.random.setup.ServiceProvider
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Response

@Suppress("IllegalIdentifier")
class ServiceApiUnitTest internal constructor() {
//    @Inject lateinit var youtubeService: YoutubeApiService

//    @InjectMocks
//    private lateinit var youtubeService: YoutubeApiService

    private val youtubeService by lazy { ServiceProvider.youtubeService }

    @Before
    fun beforeClassSetup() {
        MockitoAnnotations.initMocks(this)
    }

//    @Before
//    fun setUp() {
//        DaggerTestComponent.builder()
//                .myModule(TestModule()).build().inject(this)
//    }

    @Test
    fun `test shouldCreateChannel`() {
        val testObserver = TestObserver<Response<Channel>>()
        youtubeService.getChannel().subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `test shouldCreatePlaylist`() {
        val testObserver = TestObserver<Response<Playlist>>()
        youtubeService.getPlaylist("PLOU2XLYxmsIKNoP4CHz0m8oY8doW5fRSa").subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }

    @Test
    fun `test shouldCreateVideo`() {
        val testObserver = TestObserver<Response<Video>>()
        youtubeService.getVideo("XdZXpFBvTP8").subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
    }
}