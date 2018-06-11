package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Thumbnails(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "thumbnails_uid")
        var uid: Long,
        @Embedded var default: Default? = null,
        @Embedded var medium: Medium? = null,
        @Embedded var high: High? = null,
        @Embedded var standard: Standard? = null,
        @Embedded var maxres: Maxres? = null,
        var snippetThumbnails: List<Snippet>? = null)