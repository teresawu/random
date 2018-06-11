package co.uk.room.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Player(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "player_uid")
        var uid: Long,
        var embedHtml: String = "",
        var itemPlayer: List<Item>? = null)