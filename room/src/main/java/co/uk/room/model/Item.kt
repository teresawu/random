package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Item(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "item_uid")
        var uid: Long,
        @ColumnInfo(name = "item_kind")
        var kind: String = "",
        var etag: String = "",
        var id: String = "",
        @Embedded var snippet: Snippet? = null,
        @Embedded var player: Player? = null,
        var channelItem: List<Channel>? = null,
        var playlistItem: List<Playlist>? = null)