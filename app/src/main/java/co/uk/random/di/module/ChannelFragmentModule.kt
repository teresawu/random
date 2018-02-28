package co.uk.random.di.module

import co.uk.random.api.YoutubeApiService
import co.uk.random.error.ExceptionTransformers
import co.uk.random.util.SchedulerProvider
import co.uk.random.view.channel.ChannelViewModel
import dagger.Module
import dagger.Provides

@Module
class ChannelFragmentModule {

    @Provides
    fun provideViewModel(exceptionTransformers: ExceptionTransformers, schedulerProvider: SchedulerProvider, youtubeApiService: YoutubeApiService) = ChannelViewModel(exceptionTransformers, schedulerProvider, youtubeApiService)
}
