package co.uk.random.view.playlist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.model.Item
import co.uk.random.model.Video
import co.uk.random.util.*
import co.uk.random.util.Keys.PREF_VIDEO_ID
import co.uk.random.util.extension.createLayoutManager
import co.uk.random.view.DisposableDaggerFragment
import co.uk.random.view.home.HomeActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_playlist.view.*
import javax.inject.Inject

class PLaylistFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var playlistViewModel: PlaylistViewModel
    private val sharedPreferences by lazy { PreferenceHandler.getSharePref(context!!) }
    private var playlistList = ArrayList<Item>()
    private val playlistAdapter: PlaylistAdapter by lazy { PlaylistAdapter(playlistList, playlistDelegate = PlaylistAdapterDelegate()) }

    companion object {
        fun newInstance(): PLaylistFragment {
            return PLaylistFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_playlist, container, false)
        view.playlistRecyclerView.layoutManager = createLayoutManager()
        view.playlistRecyclerView.adapter = playlistAdapter
        gotoVideo()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isVisibleToUser) return
        onLoadingData()
    }

    private fun onLoadingData() {
        var playlistID = sharedPreferences[Keys.PREF_PLAYLIST_ID, ""]
        if (playlistID == null || playlistID.isEmpty()) return
        compositeDisposable.add(playlistViewModel.getPlaylist(playlistID)
                .subscribeBy(
                        onSuccess = {
                            playlistAdapter.refresh(it.items)
                            playlistProgressBar.visibility = View.GONE
                        },
                        onError = {
                            playlistProgressBar.setBackgroundColor(ContextCompat.getColor(playlistProgressBar.context, R.color.green))
                        }
                )
        )
    }

    private fun gotoVideo() {
        compositeDisposable.add(playlistAdapter.getClickSubject().subscribeBy(onNext = {
            val previousVideoID = sharedPreferences[Keys.PREF_VIDEO_ID, ""]
            if (!previousVideoID.equals(it)) {
                RealmHelper.delete(Video::class.java)
                sharedPreferences[Keys.PREF_VIDEO_ID] = it
            }
            (activity as HomeActivity).navigateToFragment(2)
        }))
    }
}
