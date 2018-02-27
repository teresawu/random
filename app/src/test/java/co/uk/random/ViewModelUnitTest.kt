package co.uk.random

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.util.SchedulerProvider
import co.uk.random.view.home.HomeViewModel
import co.uk.random.view.video.VideoViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


@Suppress("IllegalIdentifier")
class ViewModelUnitTest internal constructor() {
    @get: Rule
    var rule = MockitoJUnit.rule()

    @Mock
    private lateinit var exceptionTransformers: ExceptionTransformers

    @Mock
    private lateinit var schedulerProvider: SchedulerProvider

    @Mock
    private lateinit var youtubeService: YoutubeApiService

    @InjectMocks private lateinit var homeViewModel: HomeViewModel
    @InjectMocks private lateinit var videoViewModel: VideoViewModel

    @Before
    fun beforeClassSetup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test shouldCreateChannel`() {
        val channelObserver = homeViewModel.getChannel().test()
        channelObserver.assertComplete()
        channelObserver.assertNoErrors()
    }

    @Test
    fun `test shouldCreatePlaylist`() {
        val playlistObserver = homeViewModel.getPlaylist("PLOU2XLYxmsIKNoP4CHz0m8oY8doW5fRSa").test()
        playlistObserver.assertComplete()
        playlistObserver.assertNoErrors()
    }

    @Test
    fun `test shouldCreateVideo`() {
        val videoObserver = videoViewModel.getVideo("XdZXpFBvTP8").test()
        videoObserver.assertComplete()
        videoObserver.assertNoErrors()
    }
}
