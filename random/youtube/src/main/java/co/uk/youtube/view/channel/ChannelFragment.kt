package co.uk.youtube.view.channel

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.youtube.R
import co.uk.youtube.model.Item
import co.uk.youtube.model.Playlist
import co.uk.youtube.util.Keys.PREF_PLAYLIST_ID
import co.uk.youtube.util.PreferenceHandler
import co.uk.youtube.util.RealmHelper
import co.uk.youtube.util.extension.createLayoutManager
import co.uk.youtube.util.get
import co.uk.youtube.util.set
import co.uk.youtube.view.DisposableDaggerFragment
import co.uk.youtube.view.home.HomeActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_channel.view.*
import java.util.*
import javax.inject.Inject

class ChannelFragment : DisposableDaggerFragment() {
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
                RealmHelper.delete(Playlist::class.java)
                sharedPreferences[PREF_PLAYLIST_ID] = it
            }
            (activity as HomeActivity).navigateToFragment(1)
        }))
    }
}
