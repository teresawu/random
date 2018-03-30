package app.co.uk.image.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.co.uk.image.R

class ImageFragment : Fragment() {

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
