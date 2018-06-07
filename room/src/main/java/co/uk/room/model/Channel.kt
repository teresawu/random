package co.uk.room.model

import android.arch.persistence.room.Entity
import java.util.*

@Entity
data class Channel(
        @PrimaryKey(autoGenerate = false)
        val primaryKey: String = UUID.randomUUID().toString(),
        var name: String = "",
        var kind: String = "",
        var etag: String = "",
        var nextPageToken: String = "",
        var pageInfo: PageInfo? = null,
        var items: List<Item> = ArrayList()
)