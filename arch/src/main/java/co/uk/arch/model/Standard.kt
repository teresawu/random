package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Standard(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "standard_uid") var uid: Long,
        @ColumnInfo(name = "standard_url") var url: String = "",
        @ColumnInfo(name = "standard_width") var width: Int = 0,
        @ColumnInfo(name = "standard_height") var height: Int = 0,
        @ColumnInfo(name = "thumbnails_standard") var thumbnailsStandard: List<Thumbnails>? = null)