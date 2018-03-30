package app.co.uk.tensorflow.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import app.co.uk.tensorflow.view.ImageFragment
import app.co.uk.tensorflow.view.VideoFragment

class TensorPageAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {

    private val titles = arrayOf("IMAGE", "VIDEO")

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> ImageFragment.newInstance()
                1 -> VideoFragment.newInstance()
                else -> ImageFragment.newInstance()
            }

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    override fun getCount(): Int = titles.size
}