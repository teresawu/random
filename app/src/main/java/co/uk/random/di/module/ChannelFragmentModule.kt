package co.uk.random.di.module

import co.uk.youtube.util.SchedulerProvider
import co.uk.youtube.view.channel.ChannelViewModel
import co.uk.youtube.view.playlist.PlaylistViewModel
import dagger.Module
import dagger.Provides

@Module
class ChannelFragmentModule {

    @Provides
    fun provideChannelViewModel(exceptionTransformers: co.uk.youtube.error.ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: co.uk.youtube.api.YoutubeApiService) = ChannelViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)

    @Provides
    fun providePlaylistViewModel(exceptionTransformers: co.uk.youtube.error.ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: co.uk.youtube.api.YoutubeApiService) = PlaylistViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)
}

