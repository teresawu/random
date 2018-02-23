package co.uk.random.di.module

import co.uk.random.util.SchedulerProvider
import co.uk.random.view.video.VideoViewModel
import dagger.Module
import dagger.Provides

@Module
class VideoActivityModule {

    @Provides
    fun provideViewModel(schedulerProvider: SchedulerProvider) = VideoViewModel(schedulerProvider)
}
