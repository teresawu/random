package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class PageInfo(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "pageinfo_uid")
        var uid: Long,
        var totalResults: Int = 0,
        var resultsPerPage: Int = 0,
        var channelPageInfo: List<Channel>? = null,
        var playlistPageInfo: List<Playlist>? = null)