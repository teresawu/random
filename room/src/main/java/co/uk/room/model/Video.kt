package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Video(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "video_uid")
        var uid: Long,
        var firstName: String,
        var lastName: String,
        var pictureUrl: String?)