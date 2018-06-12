package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Item(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "item_uid") var uid: Long,
        @ColumnInfo(name = "item_kind") var kind: String = "",
        var etag: String = "",
        var id: String = "",
        var snippet: Snippet? = null,
        var player: Player? = null,
        @ColumnInfo(name = "channel_item") var channelItem: List<Channel>? = null,
        @ColumnInfo(name = "playlist_item") var playlistItem: List<Playlist>? = null)