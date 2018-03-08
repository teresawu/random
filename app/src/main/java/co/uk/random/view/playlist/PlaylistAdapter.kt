package co.uk.random.view.playlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.uk.random.R
import co.uk.random.model.Item

class PlaylistAdapter(private val playlist: List<Item>, private val playlistDelegate: PlaylistAdapterDelegate) : RecyclerView.Adapter<PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlaylistViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder?, position: Int) {
        val channel = playlist[position]
        holder?.let { playlistDelegate.onBind(it, channel) }
    }

    override fun getItemCount(): Int = playlist.size

    fun getClickSubject() = playlistDelegate.onClickSubject
}