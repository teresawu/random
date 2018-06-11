package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Channel(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "channel_uid")
        var uid: Long,
        var name: String = "",
        var kind: String = "",
        var etag: String = "",
        var nextPageToken: String = "",
        @Embedded var pageInfo: PageInfo? = null,
        var items: List<Item> = ArrayList()
)