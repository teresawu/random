package co.uk.random.view

import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable

abstract class DisposableDaggerFragment : DaggerFragment() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

}