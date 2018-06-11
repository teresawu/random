package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Localized(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "localized_uid") var uid: Long,
        @ColumnInfo(name = "localized_title") var title: String = "",
        @ColumnInfo(name = "localized_des") var description: String = "",
        @ColumnInfo(name = "snippet_localized") var snippetLocalized: List<Snippet>? = null)