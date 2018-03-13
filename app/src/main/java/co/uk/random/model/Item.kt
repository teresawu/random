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
}
