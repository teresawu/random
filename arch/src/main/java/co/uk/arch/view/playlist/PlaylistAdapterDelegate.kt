package co.uk.arch.view.playlist

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.uk.arch.R
import co.uk.arch.model.Item
import co.uk.arch.util.Util
import com.squareup.picasso.Picasso

class PlaylistAdapterDelegate {
    fun onBind(holder: PlaylistViewHolder, playlistItem: Item) {
        with(holder) {
            val snippet = playlistItem.snippet
            val description = Util.shortDescription(snippet?.description)
            val date = snippet?.publishedAt
            playlistTitle.text = snippet?.title
            playlistDescription.text = description
            try {
                playlistPublished.text = date?.substring(0, 10)
            } catch (exception: Exception) {
                playlistPublished.text = ""
            }

            try {
                Picasso.with(playlistTitle.context).load(snippet?.thumbnails?.high?.url).into(playlistImage)
            } catch (exception: Exception) {
                Picasso.with(playlistTitle.context)
                        .load(R.drawable.ic_menu_camera)
                        .into(playlistImage)
            }
        }
    }
}

class PlaylistViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val playlistTitle = view.findViewById<TextView>(R.id.txtPlaylistTitle)
    val playlistPublished = view.findViewById<TextView>(R.id.txtPlaylistPublished)
    val playlistDescription = view.findViewById<TextView>(R.id.txtPlaylistDescription)
    val playlistImage = view.findViewById<ImageView>(R.id.imgPlaylist)
    val playlistLayout = view.findViewById<ConstraintLayout>(R.id.playlistLayout)
}