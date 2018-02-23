package co.uk.random.di

import co.uk.random.di.module.HomeActivityModule
import co.uk.random.di.module.VideoActivityModule
import co.uk.random.view.home.HomeActivity
import co.uk.random.view.video.VideoActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun bindHomeActivity(): HomeActivity


    @ContributesAndroidInjector(modules = [VideoActivityModule::class])
    abstract fun bindVideoActivity(): VideoActivity
}