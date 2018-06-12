package co.uk.arch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Player(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "player_uid") var uid: Long,
        @ColumnInfo(name = "embed_html") var embedHtml: String = "",
        @ColumnInfo(name = "item_player") var itemPlayer: List<Item>? = null)