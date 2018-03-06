package co.uk.random.view.channel

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.uk.random.R

class ChannelViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val channelTitle = view.findViewById<TextView>(R.id.txtChannelTitle)
    val channelNumber = view.findViewById<TextView>(R.id.txtChannelNumber)
    val channelSubscribe = view.findViewById<TextView>(R.id.txtChannelSubscribe)
    val channelImage = view.findViewById<ImageView>(R.id.imgChannel)

}