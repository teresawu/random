package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class Item() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 1100
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
    val channel: RealmResults<Channel>? = null
    @LinkingObjects("items")
    val playlist: RealmResults<Playlist>? = null
    @LinkingObjects("items")
    val video: RealmResults<Video>? = null

    constructor(kind: String, etag: String, id: String, snippet: Snippet?, player: Player?) : this() {
        this.kind = kind
        this.etag = etag
        this.id = id
        this.snippet = snippet
        this.player = player
    }
}
