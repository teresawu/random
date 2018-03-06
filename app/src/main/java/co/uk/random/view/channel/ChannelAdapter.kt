package co.uk.random.view.channel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.model.Channel

class ChannelAdapter(private val channels: List<Channel>, private val channelDelegate: ChannelAdapterDelegate) : RecyclerView.Adapter<ChannelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ChannelViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder?, position: Int) {
        val channel = channels[position]
        holder?.let { channelDelegate.onBind(it, channel) }
    }

    override fun getItemCount(): Int = channels.size
}