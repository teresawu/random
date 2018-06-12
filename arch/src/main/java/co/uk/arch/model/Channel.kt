package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Channel(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "channel_uid") var uid: Long,
        var name: String = "",
        var kind: String = "",
        var etag: String = "",
        @ColumnInfo(name = "next_page_token") var nextPageToken: String = "",
        var pageInfo: PageInfo? = null,
        var items: List<Item> = ArrayList()
)