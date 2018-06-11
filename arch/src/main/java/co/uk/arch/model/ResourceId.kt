package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class ResourceId(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "resourceid_uid") var uid: Long,
        var kind: String = "",
        @ColumnInfo(name = "video_id") var videoId: String = "",
        @ColumnInfo(name = "snippet_resource_id") var snippetResourceId: List<Snippet>? = null)