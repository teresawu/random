package co.uk.youtube.view.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import co.uk.youtube.R
import co.uk.youtube.model.Item
import io.reactivex.subjects.PublishSubject
import io.realm.RealmList

class PlaylistAdapter(private val playlist: ArrayList<Item>, private val playlistDelegate: PlaylistAdapterDelegate) : androidx.recyclerview.widget.RecyclerView.Adapter<PlaylistViewHolder>() {
    val onClickSubject = PublishSubject.create<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.let { playlistDelegate.onBind(it, playlist[position]) }
        holder.playlistLayout?.setOnClickListener {
            onClickSubject.onNext(position)
        }
    }

    override fun getItemCount(): Int = playlist.size

    fun refresh(data: RealmList<Item>?) {
        if (data == null || data.size == 0) return
        playlist.clear()
        playlist.addAll(data)
        notifyDataSetChanged()
    }
}