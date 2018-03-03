package co.uk.random.view.channel

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import co.uk.random.R

class ChannelViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val videoView = view.findViewById<ImageView>(R.id.videoThumbnail)
    val btnPlayer = view.findViewById<ImageView>(R.id.btnPlayer)

}