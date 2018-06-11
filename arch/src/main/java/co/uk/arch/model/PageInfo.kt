package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class PageInfo(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "pageinfo_uid") var uid: Long,
        @ColumnInfo(name = "total_results") var totalResults: Int = 0,
        @ColumnInfo(name = "results_per_page") var resultsPerPage: Int = 0,
        @ColumnInfo(name = "channel_page_info") var channelPageInfo: List<Channel>? = null,
        @ColumnInfo(name = "playlist_page_info") var playlistPageInfo: List<Playlist>? = null)