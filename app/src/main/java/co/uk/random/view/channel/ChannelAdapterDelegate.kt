package co.uk.random.view.channel

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.uk.random.R
import co.uk.random.model.Item
import com.squareup.picasso.Picasso

class ChannelAdapterDelegate() {
    fun onBind(holder: ChannelViewHolder, channel: Item) {
        with(holder) {
            channelTitle.text = channel.snippet?.channelTitle
            channelDescription.text = channel.snippet?.description
            val date = channel.snippet?.publishedAt
            channelPublished.text = date?.substring(0, 10)
            Picasso.with(channelTitle.context).load(channel.snippet?.thumbnails?.default?.url).into(channelImage)
        }
    }
}

class ChannelViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    val channelTitle = view.findViewById<TextView>(R.id.txtChannelTitle)
    val channelPublished = view.findViewById<TextView>(R.id.txtChannelPublished)
    val channelDescription = view.findViewById<TextView>(R.id.txtChannelDescription)
    val channelImage = view.findViewById<ImageView>(R.id.imgChannel)
}