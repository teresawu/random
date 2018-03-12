package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Item() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()
    @SerializedName("kind")
    var kind: String = ""
        private set
    @SerializedName("etag")
    var etag: String = ""
        private set
    @SerializedName("id")
    var id: String = ""
        private set
    @SerializedName("snippet")
    var snippet: Snippet? = null
        private set
    @SerializedName("player")
    var player: Player? = null

    @LinkingObjects("items")
    val channelItem: RealmResults<Channel>? = null
    @LinkingObjects("items")
    val playlistItem: RealmResults<Playlist>? = null

    constructor(kind: String, etag: String, id: String, snippet: Snippet?, player: Player?) : this() {
        this.kind = kind
        this.etag = etag
        this.id = id
        this.snippet = snippet
        this.player = player
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Item) return false

        if (primaryKey != other.primaryKey) return false
        if (kind != other.kind) return false
        if (etag != other.etag) return false
        if (id != other.id) return false
        if (snippet != other.snippet) return false
        if (player != other.player) return false
        if (channelItem != other.channelItem) return false
        if (playlistItem != other.playlistItem) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey.hashCode()
        result = 31 * result + kind.hashCode()
        result = 31 * result + etag.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + (snippet?.hashCode() ?: 0)
        result = 31 * result + (player?.hashCode() ?: 0)
        result = 31 * result + (channelItem?.hashCode() ?: 0)
        result = 31 * result + (playlistItem?.hashCode() ?: 0)
        return result
    }


}
