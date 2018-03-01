package co.uk.random.view.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.view.DisposableDaggerFragment
import javax.inject.Inject

class ChannelFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var channelViewModel: ChannelViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_channel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): ChannelFragment {
            return ChannelFragment()
        }
    }
}
