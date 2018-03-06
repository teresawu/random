package co.uk.random.view.channel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.model.Channel
import co.uk.random.util.extension.createLayoutManager
import co.uk.random.view.DisposableDaggerFragment
import kotlinx.android.synthetic.main.fragment_channel.view.*
import javax.inject.Inject

class ChannelFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var channelViewModel: ChannelViewModel
    private var channelList = listOf<Channel>()
    private val channelAdapter: ChannelAdapter by lazy { ChannelAdapter(channelList, ChannelAdapterDelegate()) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_channel, container, false)
        view.channelRecyclerView.layoutManager = createLayoutManager()
        view.channelRecyclerView.adapter = channelAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(): ChannelFragment {
            return ChannelFragment()
        }
    }
}
