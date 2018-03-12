package co.uk.random.view.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.util.PreferenceHandler
import co.uk.random.view.DisposableDaggerFragment
import com.squareup.picasso.Picasso
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject

class VideoFragment : DisposableDaggerFragment() {
    @Inject
    lateinit var videoViewModel: VideoViewModel
    private val sharedPreferences by lazy { PreferenceHandler.getSharePref(context!!) }

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
//        onLoadingData()
    }

    private fun onLoadingData() {
        val video = videoViewModel.getVideo().subscribeBy(
                onSuccess = {
                    val snippet = it.items.first()?.snippet
                    txtVideoTitle.text = snippet?.title
                    txtVideoDescription.text = snippet?.description
                    try {
                        Picasso.with(imgVideoPlayer.context).load(snippet?.thumbnails?.high?.url).into(imgVideoPlayer)
                    } catch (exception: Exception) {
                        Picasso.with(imgVideoPlayer.context)
                                .load(R.drawable.ic_menu_camera)
                                .into(imgVideoPlayer)
                    }
                    imgVideoPlayer.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://www.youtube.com/watch?v=" + snippet?.resourceId?.videoId)))
                    }
                })
    }
}
