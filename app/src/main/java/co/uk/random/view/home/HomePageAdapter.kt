package co.uk.random.view.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import app.co.uk.tensorflow.view.ImageFragment
import co.uk.random.view.channel.ChannelFragment
import co.uk.random.view.playlist.PLaylistFragment
import co.uk.random.view.video.VideoFragment

class HomePageAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager) {

    private val titles = arrayOf("IMAGE", "CHANNEL", "PLAYLIST", "VIDEOS")


    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> ImageFragment.newInstance()
                1 -> ChannelFragment.newInstance()
                2 -> PLaylistFragment.newInstance()
                3 -> VideoFragment.newInstance()
                else -> ImageFragment.newInstance()
            }

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    override fun getCount(): Int = titles.size
}