package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class ResourceId(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "resourceid_uid")
        var uid: Long,
        var kind: String = "",
        var videoId: String = "",
        var snippetResourceId: List<Snippet>? = null)