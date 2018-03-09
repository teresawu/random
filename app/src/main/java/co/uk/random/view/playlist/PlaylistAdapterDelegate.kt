package co.uk.random.view.playlist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.uk.random.R
import co.uk.random.model.Item
import co.uk.random.util.Util
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject


class PlaylistAdapterDelegate {
    val onClickSubject = PublishSubject.create<String>()

    fun onBind(holder: PlaylistViewHolder, playlist: Item) {
        with(holder) {
            val snippet = playlist.snippet
            val description = Util.shortDescription(snippet?.description)
            val date = snippet?.publishedAt
            playlistTitle.text = snippet?.title
            playlistDescription.text = description
            playlistPublished.text = date?.substring(0, 10)
            try {
                Picasso.with(playlistTitle.context).load(snippet?.thumbnails?.high?.url).into(playlistImage)
            } catch (exception: Exception) {
                Picasso.with(playlistTitle.context)
                        .load(R.drawable.ic_menu_camera)
                        .into(playlistImage)
            }
            viewItem.setOnClickListener { onClickSubject.onNext(snippet!!.playlistId) }
        }
    }
}

class PlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val playlistTitle = view.findViewById<TextView>(R.id.txtPlaylistTitle)
    val playlistPublished = view.findViewById<TextView>(R.id.txtPlaylistPublished)
    val playlistDescription = view.findViewById<TextView>(R.id.txtPlaylistDescription)
    val playlistImage = view.findViewById<ImageView>(R.id.imgPlaylist)
    val viewItem = view
}