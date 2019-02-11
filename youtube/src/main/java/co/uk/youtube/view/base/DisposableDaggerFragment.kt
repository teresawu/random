package co.uk.youtube.view.base

import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class DisposableDaggerFragment : DaggerFragment() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }

}