package co.uk.random.di.module

import co.uk.youtube.util.SchedulerProvider
import co.uk.youtube.view.channel.state.ChannelRepo
import co.uk.youtube.view.playlist.PlaylistViewModel
import dagger.Module
import dagger.Provides

@Module
class ChannelFragmentModule {

    @Provides
    fun provideChannelRepo(exceptionTransformers: co.uk.youtube.error.ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: co.uk.youtube.api.YoutubeApiService) = ChannelRepo(exceptionTransformers, schedulerProvider, youtubeApiService)

    @Provides
    fun providePlaylistViewModel(exceptionTransformers: co.uk.youtube.error.ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: co.uk.youtube.api.YoutubeApiService) = PlaylistViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)
}

