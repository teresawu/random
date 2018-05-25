package co.uk.random.di.module

import co.uk.youtube.api.YoutubeApiService
import co.uk.youtube.error.ExceptionTransformers
import co.uk.youtube.util.SchedulerProvider
import co.uk.youtube.view.home.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun provideViewModel(exceptionTransformers: ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: YoutubeApiService) = HomeViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)
}
