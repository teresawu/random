package co.uk.random.di.module

import co.uk.room.api.RoomApiService
import co.uk.room.error.ExceptionTransformers
import co.uk.room.util.SchedulerProvider
import co.uk.room.view.home.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivitymodule {

    @Provides
    fun provideViewModel(exceptionTransformers: ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: RoomApiService) = MainViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)
}
