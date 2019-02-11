package co.uk.youtube.view.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import co.uk.youtube.R
import co.uk.youtube.databinding.FragmentPlaylistBinding
import co.uk.youtube.model.Item
import co.uk.youtube.util.Keys
import co.uk.youtube.util.Keys.PREF_VIDEO_ID
import co.uk.youtube.util.PreferenceHandler
import co.uk.youtube.util.extension.createLayoutManager
import co.uk.youtube.util.get
import co.uk.youtube.util.set
import co.uk.youtube.view.base.DisposableDaggerFragment
import co.uk.youtube.view.home.HomeActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_playlist.*
import javax.inject.Inject

class PLaylistFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var playlistViewModel: PlaylistViewModel
    private val sharedPreferences by lazy { PreferenceHandler.getSharePref(context!!) }
    private var playlistList = ArrayList<Item>()
    private val playlistAdapter: PlaylistAdapter by lazy { PlaylistAdapter(playlistList, playlistDelegate = PlaylistAdapterDelegate()) }
    private lateinit var playlistBinding: FragmentPlaylistBinding

    companion object {
        fun newInstance(): PLaylistFragment {
            return PLaylistFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        playlistBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_playlist, container, false)
        playlistBinding.playlist = playlistViewModel
        return playlistBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playlistRecyclerView.layoutManager = createLayoutManager()
        playlistRecyclerView.adapter = playlistAdapter
        gotoVideo()
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
                        }
                )
        )
    }

    private fun gotoVideo() {
        compositeDisposable.add(playlistAdapter.onClickSubject.subscribeBy(onNext = {
            sharedPreferences[PREF_VIDEO_ID] = it
            (activity as HomeActivity).navigateToFragment(2)
        }))
    }
}
