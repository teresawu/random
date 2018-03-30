package co.uk.random.di

import app.co.uk.tensorflow.view.ImageFragment
import co.uk.random.di.module.ChannelFragmentModule
import co.uk.random.di.module.ImageFragmentModule
import co.uk.random.di.module.PlaylistFragmentModule
import co.uk.random.view.channel.ChannelFragment
import co.uk.random.view.playlist.PLaylistFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [ChannelFragmentModule::class])
    abstract fun bindChannelFragment(): ChannelFragment

    @ContributesAndroidInjector(modules = [PlaylistFragmentModule::class])
    abstract fun bindPlaylistFragment(): PLaylistFragment

    @ContributesAndroidInjector(modules = [ImageFragmentModule::class])
    abstract fun bindImageFragment(): ImageFragment
}