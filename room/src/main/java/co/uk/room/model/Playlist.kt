package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Playlist(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "playlist_uid")
        var uid: Long,
        var kind: String = "",
        var etag: String = "",
        @Embedded var pageInfo: PageInfo? = null,
        var items: List<Item> = ArrayList())
