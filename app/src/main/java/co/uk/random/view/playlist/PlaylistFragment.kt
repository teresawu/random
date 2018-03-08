package co.uk.random.view.playlist

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.model.Item
import co.uk.random.util.Keys.PLAYLIST_ID
import co.uk.random.util.RealmHelper
import co.uk.random.util.extension.createLayoutManager
import co.uk.random.view.DisposableDaggerFragment
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_playlist.view.*
import java.util.*
import javax.inject.Inject

class PLaylistFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var playlistViewModel: PlaylistViewModel
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val playlistID = arguments?.getString(PLAYLIST_ID)
        onLoadingData(playlistID)
    }

    fun onLoadingData(playlistID: String?) {
        compositeDisposable.add(playlistViewModel.getPlaylist(if (playlistID == null) "" else playlistID)
                .subscribeBy(
                        onSuccess = {
                            playlistList.clear()
                            val playlist = it
                            playlist.items.forEach {
                                val item = it
                                playlistList.add(item)
                            }
                            RealmHelper.copyOrUpdate(it)
                            playlistAdapter.notifyDataSetChanged()
                            playlistProgressBar.visibility = View.GONE
                        },
                        onError = {
                            playlistProgressBar.setBackgroundColor(ContextCompat.getColor(playlistProgressBar.context, R.color.green))
                        }
                )
        )
    }

}
