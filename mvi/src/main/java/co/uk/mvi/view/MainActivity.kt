package co.uk.mvi.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.uk.mvi.R
import co.uk.mvi.intent.MainViewState
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        render(mainViewModel.viewStateReducer())
    }

    fun render(viewState: MainViewState<Boolean>) {
        viewState.loadSubject.subscribeBy(onNext = {
            if (it) {
                progressBar.visibility = View.VISIBLE
                teamButton.isEnabled = false
            }
        })

        viewState.loadedSubject.subscribeBy(onNext = {
            if (it) {
                progressBar.visibility = View.GONE
                teamButton.isEnabled = true
            }
        })

        viewState.loadedSubject.subscribeBy(onNext = {
            if (it) {
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })

        viewState.buttonSubject.subscribeBy(onNext = {
            if (it) {
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
