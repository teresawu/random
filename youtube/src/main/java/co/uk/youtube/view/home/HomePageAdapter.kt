package co.uk.youtube.view.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import co.uk.youtube.view.channel.ChannelFragment
import co.uk.youtube.view.playlist.PLaylistFragment
import co.uk.youtube.view.video.VideoFragment

class HomePageAdapter(supportFragmentManager: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(supportFragmentManager) {

    private val titles = arrayOf("CHANNEL", "PLAYLIST", "VIDEOS")


    override fun getItem(position: Int): androidx.fragment.app.Fragment =
            when (position) {
                0 -> ChannelFragment.newInstance()
                1 -> PLaylistFragment.newInstance()
                2 -> VideoFragment.newInstance()
                else -> ChannelFragment.newInstance()
            }

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    override fun getCount(): Int = titles.size
}