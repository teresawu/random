package co.uk.random.di

import co.uk.random.di.module.ReceiptsActivityModule
import co.uk.spltech.receipts.ReceiptsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ReceiptsActivityModule::class])
    abstract fun bindReceiptsActivity(): ReceiptsActivity
}