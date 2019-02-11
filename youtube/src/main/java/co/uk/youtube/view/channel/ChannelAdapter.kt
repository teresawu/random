package co.uk.youtube.view.channel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.uk.youtube.R
import co.uk.youtube.model.Item
import co.uk.youtube.util.Util
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import io.realm.RealmList

class ChannelAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<ChannelViewHolder>() {
    val onClickSubject = PublishSubject.create<String>()

    var channelList = RealmList<Item>()
        set(value) {
            if (value != field) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_channel, parent, false)
        return ChannelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChannelViewHolder, position: Int) {
        val channel = channelList[position]
        holder.let { onBind(it, channel) }
    }

    override fun getItemCount(): Int = channelList.size

    fun onBind(holder: ChannelViewHolder, channel: Item?) {
        with(holder) {
            val snippet = channel?.snippet
            val description = Util.shortDescription(snippet?.description)
            channelTitle.text = snippet?.title
            channelDescription.text = description
            val date = snippet?.publishedAt
            channelPublished.text = date?.substring(0, 10)
            try {
                Picasso.with(channelTitle.context).load(snippet?.thumbnails?.high?.url).into(channelImage)
            } catch (exception: Exception) {
                Picasso.with(channelTitle.context)
                        .load(R.drawable.ic_menu_camera)
                        .into(channelImage)
            }
            viewItem.setOnClickListener { onClickSubject.onNext(channel!!.id) }
        }
    }
}

class ChannelViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val channelTitle = view.findViewById<TextView>(R.id.txtChannelTitle)
    val channelPublished = view.findViewById<TextView>(R.id.txtChannelPublished)
    val channelDescription = view.findViewById<TextView>(R.id.txtChannelDescription)
    val channelImage = view.findViewById<ImageView>(R.id.imgChannel)
    val viewItem = view
}