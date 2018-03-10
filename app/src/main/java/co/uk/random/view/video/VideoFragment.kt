package co.uk.random.view.video

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.model.Video
import co.uk.random.util.Keys
import co.uk.random.util.PreferenceHandler
import co.uk.random.util.get
import co.uk.random.view.DisposableDaggerFragment
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_playlist.*
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject

class VideoFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var videoViewModel: VideoViewModel
    private val sharedPreferences by lazy { PreferenceHandler.getSharePref(context!!) }
    private lateinit var video: Video

    companion object {
        fun newInstance(): VideoFragment {
            return VideoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_video, container, false)
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isVisibleToUser) return
        onLoadingData()
    }

    private fun onLoadingData() {
        var videoID = sharedPreferences[Keys.PREF_VIDEO_ID, ""]
        if (videoID == null || videoID.isEmpty()) return
        compositeDisposable.add(videoViewModel.getVideo(videoID)
                .subscribeBy(
                        onSuccess = {
                            videoProgressBar.visibility = View.GONE
                            videoPlayerView.text = it.items[0]?.id
                        },
                        onError = {
                            videoProgressBar.setBackgroundColor(ContextCompat.getColor(playlistProgressBar.context, R.color.green))
                        }
                )
        )
    }
}
