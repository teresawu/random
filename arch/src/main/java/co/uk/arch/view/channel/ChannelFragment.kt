package co.uk.arch.view.channel

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.arch.R
import co.uk.arch.model.Item
import co.uk.arch.util.Keys.PREF_PLAYLIST_ID
import co.uk.arch.util.PreferenceHandler
import co.uk.arch.util.extension.createLayoutManager
import co.uk.arch.util.get
import co.uk.arch.util.set
import co.uk.arch.view.DisposableFragment
import co.uk.arch.view.main.MainActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_channel.view.*
import java.util.*
import javax.inject.Inject

class ChannelFragment : DisposableFragment() {
    @Inject
    lateinit var channelViewModel: ChannelViewModel
    private val sharedPreferences by lazy { PreferenceHandler.getSharePref(context!!) }
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
        gotoPlaylist()
    }

    private fun onLoadingData() {
        compositeDisposable.add(channelViewModel.getChannel()
                .subscribeBy(
                        onSuccess = {
                            channelAdapter.refresh(it.items)
                            channelProgressBar.visibility = View.GONE
                            sharedPreferences[PREF_PLAYLIST_ID] = it.items.first()?.id
                        },
                        onError = {
                            channelProgressBar.setBackgroundColor(ContextCompat.getColor(channelProgressBar.context, R.color.green))
                        }
                )
        )
    }

    private fun gotoPlaylist() {
        compositeDisposable.add(channelAdapter.getClickSubject().subscribeBy(onNext = {
            val previousPlaylistID = sharedPreferences[PREF_PLAYLIST_ID, ""]
            if (!previousPlaylistID.equals(it)) {
//                RealmHelper.delete(Playlist::class.java)
                sharedPreferences[PREF_PLAYLIST_ID] = it
            }
            (activity as MainActivity).navigateToFragment(1)
        }))
    }
}
