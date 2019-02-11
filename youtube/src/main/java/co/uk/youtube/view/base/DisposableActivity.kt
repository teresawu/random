package co.uk.youtube.view.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class DisposableActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    protected fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

}