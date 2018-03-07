package co.uk.random.view.channel

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.model.Item
import co.uk.random.util.RealmHelper
import co.uk.random.util.extension.createLayoutManager
import co.uk.random.view.DisposableDaggerFragment
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_channel.view.*
import java.util.*
import javax.inject.Inject

class ChannelFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var channelViewModel: ChannelViewModel
    private var channelList = ArrayList<Item>()
    private val channelAdapter: ChannelAdapter by lazy { ChannelAdapter(channelList, ChannelAdapterDelegate()) }

    companion object {
        fun newInstance(): ChannelFragment {
            return ChannelFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_channel, container, false)
        view.channelRecyclerView.layoutManager = createLayoutManager()
        view.channelRecyclerView.adapter = channelAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoadingData()
    }


    fun onLoadingData() {
        channelViewModel.getChannel()
                .subscribeBy(
                        onSuccess = {
                            channelList.clear()
                            it.items.forEach { channelList.add(it) }
                            RealmHelper.copyOrUpdate(it)
                            channelAdapter.notifyDataSetChanged()
                            channelProgressBar.visibility = View.GONE
                        },
                        onError = {
                            channelProgressBar.setBackgroundColor(ContextCompat.getColor(channelProgressBar.context, R.color.green))
                        }
                )
    }
}
