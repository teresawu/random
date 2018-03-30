package app.co.uk.image.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.co.uk.image.R
import dagger.android.support.DaggerFragment

class ImageFragment : DaggerFragment() {

    companion object {
        fun newInstance(): ImageFragment {
            return ImageFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        return view
    }
}
