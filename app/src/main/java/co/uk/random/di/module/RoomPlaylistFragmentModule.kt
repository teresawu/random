package co.uk.random.di.module

import co.uk.room.api.RoomApiService
import co.uk.room.error.ExceptionTransformers
import co.uk.room.util.SchedulerProvider
import co.uk.room.view.playlist.PlaylistViewModel
import dagger.Module
import dagger.Provides

@Module
class RoomPlaylistFragmentModule {

    @Provides
    fun providePlaylistViewModel(exceptionTransformers: ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: RoomApiService) = PlaylistViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)

}
