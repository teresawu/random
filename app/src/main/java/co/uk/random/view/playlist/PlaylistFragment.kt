package co.uk.random.view.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.view.DisposableDaggerFragment
import co.uk.random.view.channel.ChannelFragment
import javax.inject.Inject

class PLaylistFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var playlistViewModel: PlaylistViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_playlist, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): PLaylistFragment {
            return PLaylistFragment()
        }
    }
}
