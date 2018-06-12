package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Snippet(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "snippet_uid") var uid: Long,
        @ColumnInfo(name = "published_at") var publishedAt: String = "",
        @ColumnInfo(name = "channel_id") var channelId: String = "",
        @ColumnInfo(name = "snippet_title") var title: String = "",
        var description: String = "",
        var thumbnails: Thumbnails? = null,
        @ColumnInfo(name = "channel_title") var channelTitle: String = "",
        @ColumnInfo(name = "playlist_id") var playlistId: String = "",
        var position: Int = 0,
        @Embedded var resourceId: ResourceId? = null,
        var localized: Localized? = null,
        @ColumnInfo(name = "item_snippet") var itemSnippet: List<Item>? = null)

data class ResourceId(
        var kind: String = "",
        var videoId: String = "",
        var snippetResourceId: List<Snippet>? = null)