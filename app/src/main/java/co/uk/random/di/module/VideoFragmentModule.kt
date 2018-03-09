package co.uk.random.di.module

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.util.SchedulerProvider
import co.uk.random.view.video.VideoViewModel
import dagger.Module
import dagger.Provides

@Module
class VideoFragmentModule {

    @Provides
    fun provideVideoViewModel(exceptionTransformers: ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: YoutubeApiService) = VideoViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)
}
