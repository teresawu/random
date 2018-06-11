package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Snippet(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "snippet_uid")
        var uid: Long,
        var publishedAt: String = "",
        var channelId: String = "",
        @ColumnInfo(name = "snippet_title")
        var title: String = "",
        var description: String = "",
        @Embedded var thumbnails: Thumbnails? = null,
        var channelTitle: String = "",
        var playlistId: String = "",
        var position: Int = 0,
        @Embedded var resourceId: ResourceId? = null,
        @Embedded var localized: Localized? = null,
        var itemSnippet: List<Item>? = null)