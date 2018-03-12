package co.uk.random.di.module

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.util.SchedulerProvider
import co.uk.random.view.playlist.PlaylistViewModel
import dagger.Module
import dagger.Provides

@Module
class PlaylistFragmentModule {

    @Provides
    fun providePlaylistViewModel(exceptionTransformers: ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: YoutubeApiService) = PlaylistViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)

}
