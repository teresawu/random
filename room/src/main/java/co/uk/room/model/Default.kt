package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Default(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "default_uid")
        var uid: Long,
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0)