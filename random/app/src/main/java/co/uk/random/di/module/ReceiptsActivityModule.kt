package co.uk.random.di.module

import co.uk.spltech.receipts.ReceiptsViewModel
import dagger.Module
import dagger.Provides

@Module
class ReceiptsActivityModule {

    @Provides
    fun provideViewModel() = ReceiptsViewModel()
}
