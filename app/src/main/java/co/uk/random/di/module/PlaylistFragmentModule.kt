package co.uk.random.di.module

import co.uk.youtube.api.YoutubeApiService
import co.uk.youtube.error.ExceptionTransformers
import co.uk.youtube.util.SchedulerProvider
import co.uk.youtube.view.playlist.PlaylistViewModel
import dagger.Module
import dagger.Provides

@Module
class PlaylistFragmentModule {

    @Provides
    fun providePlaylistViewModel(exceptionTransformers: ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: YoutubeApiService) = PlaylistViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)

}
