package co.uk.random

import co.uk.random.setup.ModelProvider
import co.uk.random.setup.SchedulerRule
import co.uk.random.view.channel.ChannelViewModel
import co.uk.random.view.playlist.PlaylistViewModel
import co.uk.random.view.video.VideoViewModel
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner


@Suppress("IllegalIdentifier")
@RunWith(MockitoJUnitRunner::class)
class ViewModelUnitTest internal constructor() {
    @get: Rule
    var rule = MockitoJUnit.rule()
    @get: ClassRule
    val schedulers = SchedulerRule()

    private val schedulerProvider by lazy { ModelProvider.getSchedulerProvider() }
    private val exceptionTransformers by lazy { ModelProvider.getExceptionTransformers() }
    private val youtubeService by lazy { ModelProvider.youtubeService }
    private val playlistViewModel by lazy { PlaylistViewModel(exceptionTransformers, schedulerProvider, youtubeService) }
    private val videoViewModel by lazy { VideoViewModel(exceptionTransformers, schedulerProvider, youtubeService) }
    private val channelViewModel by lazy { ChannelViewModel(exceptionTransformers, schedulerProvider, youtubeService) }

    @Before
    fun beforeClassSetup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test shouldCreateChannel`() {
        val channelObserver = channelViewModel.getChannel().test()
        channelObserver.assertComplete()
        channelObserver.assertNoErrors()
    }

    @Test
    fun `test shouldCreatePlaylist`() {
        val playlistObserver = playlistViewModel.getPlaylist("PLOU2XLYxmsIKNoP4CHz0m8oY8doW5fRSa").test()
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
