package co.uk.random.di.module

import co.uk.random.util.SchedulerProvider
import co.uk.random.view.home.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    fun provideViewModel(schedulerProvider: SchedulerProvider) = HomeViewModel(schedulerProvider)
}
