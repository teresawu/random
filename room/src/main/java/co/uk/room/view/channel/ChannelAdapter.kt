package co.uk.room.view.channel

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.uk.room.R
import co.uk.room.model.Item

class ChannelAdapter(private val channels: ArrayList<Item>, private val channelDelegate: ChannelAdapterDelegate) : RecyclerView.Adapter<ChannelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channels[position]
        holder.let { channelDelegate.onBind(it, channel) }
    }

    override fun getItemCount(): Int = channels.size

    fun getClickSubject() = channelDelegate.onClickSubject

    fun refresh(data: List<Item>?) {
        if (data == null || data.size == 0) return
        channels.clear()
        channels.addAll(data)
        notifyDataSetChanged()
    }
}