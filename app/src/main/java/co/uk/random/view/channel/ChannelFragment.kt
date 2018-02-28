package co.uk.random.view.channel

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.uk.random.R
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ChannelFragment : AppCompatActivity() {
    @Inject
    lateinit var channelViewModel: ChannelViewModel
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_channel)
    }
}
