package co.uk.random.view.playlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.uk.random.R
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class PLaylistFragment : AppCompatActivity() {
    @Inject
    lateinit var playlistViewModel: PlaylistViewModel
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_playlist)
    }
}
