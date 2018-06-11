package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Medium(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "medium_uid")
        var uid: Long,
        @ColumnInfo(name = "medium_url")
        var url: String = "",
        @ColumnInfo(name = "medium_width")
        var width: Int = 0,
        @ColumnInfo(name = "medium_height")
        var height: Int = 0,
        var thumbnailsMedium: List<Thumbnails>? = null)