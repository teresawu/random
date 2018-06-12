package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Thumbnails(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "thumbnails_uid") var uid: Long,
        @Embedded var default: Default? = null,
        @Embedded var medium: Medium? = null,
        @Embedded var high: High? = null,
        @Embedded var standard: Standard? = null,
        @Embedded var maxres: Maxres? = null,
        @ColumnInfo(name = "snippet_thumbnails") var snippetThumbnails: List<Snippet>? = null)

data class Default(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0)

data class High(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        var thumbnailsHigh: List<Thumbnails>? = null)

data class Maxres(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        var thumbnailsMaxres: List<Thumbnails>? = null)

data class Medium(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        var thumbnailsMedium: List<Thumbnails>? = null)


data class Standard(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        var thumbnailsStandard: List<Thumbnails>? = null)