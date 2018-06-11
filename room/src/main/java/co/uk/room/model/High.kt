package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class High(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "high_uid")
        var uid: Long,
        @ColumnInfo(name = "high_url")
        var url: String = "",
        @ColumnInfo(name = "high_width")
        var width: Int = 0,
        @ColumnInfo(name = "high_height")
        var height: Int = 0,
        var thumbnailsHigh: List<Thumbnails>? = null)