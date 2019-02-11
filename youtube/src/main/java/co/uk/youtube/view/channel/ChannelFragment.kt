package co.uk.youtube.view.channel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import co.uk.youtube.R
import co.uk.youtube.model.Playlist
import co.uk.youtube.util.Keys.PREF_PLAYLIST_ID
import co.uk.youtube.util.PreferenceHandler
import co.uk.youtube.util.RealmHelper
import co.uk.youtube.util.extension.createLayoutManager
import co.uk.youtube.util.get
import co.uk.youtube.util.set
import co.uk.youtube.view.base.DisposableDaggerFragment
import co.uk.youtube.view.channel.state.ChannelState
import co.uk.youtube.view.home.HomeActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_channel.*
import kotlinx.android.synthetic.main.fragment_channel.view.*
import javax.inject.Inject

class ChannelFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var channelViewModel: ChannelViewModel
    private val sharedPreferences by lazy { PreferenceHandler.getSharePref(context!!) }
    private val channelAdapter: ChannelAdapter by lazy { ChannelAdapter() }

    companion object {
        fun newInstance(): ChannelFragment {
            return ChannelFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_channel, container, false)
        view.channelRecyclerView.layoutManager = createLayoutManager()
        view.channelRecyclerView.adapter = channelAdapter
        lifecycle.addObserver(channelViewModel)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add(channelViewModel.stateSubject.subscribeBy(
                onNext = ::onStateUpdate,
                onError = { it.printStackTrace()}
        ))
    }

    private fun onStateUpdate(state: ChannelState) {
        Log.i("ChannelFragment", "onStateUpdate state = " + state.loaded)
        channelAdapter.channelList = state.channel.items
        if (state.loading) {
            channelProgressBar.visibility = View.VISIBLE
        } else if (state.loaded) {
            channelProgressBar.visibility = View.GONE
            sharedPreferences[PREF_PLAYLIST_ID] = state.channel.items.first()?.id
        } else {
            channelProgressBar.setBackgroundColor(ContextCompat.getColor(channelProgressBar.context, R.color.green))
        }

        add(channelAdapter.onClickSubject.subscribeBy(onNext = {
            val previousPlaylistID = sharedPreferences[PREF_PLAYLIST_ID, ""]
            if (!previousPlaylistID.equals(it)) {
                RealmHelper.delete(Playlist::class.java)
                sharedPreferences[PREF_PLAYLIST_ID] = it
            }
            (activity as HomeActivity).navigateToFragment(1)
        }))
    }
}
