package co.uk.random.di

import co.uk.random.di.module.ChannelFragmentModule
import co.uk.random.di.module.PlaylistFragmentModule
import co.uk.youtube.view.channel.ChannelFragment
import co.uk.youtube.view.playlist.PLaylistFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector(modules = [ChannelFragmentModule::class])
    abstract fun bindChannelFragment(): ChannelFragment

    @ContributesAndroidInjector(modules = [PlaylistFragmentModule::class])
    abstract fun bindPlaylistFragment(): PLaylistFragment
}