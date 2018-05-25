package co.uk.youtube.model

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
    var snippet: co.uk.youtube.model.Snippet? = null
        private set
    @SerializedName("player")
    var player: co.uk.youtube.model.Player? = null

    @LinkingObjects("items")
    val channelItem: RealmResults<co.uk.youtube.model.Channel>? = null
    @LinkingObjects("items")
    val playlistItem: RealmResults<co.uk.youtube.model.Playlist>? = null

    constructor(kind: String, etag: String, id: String, snippet: co.uk.youtube.model.Snippet?, player: co.uk.youtube.model.Player?) : this() {
        this.kind = kind
        this.etag = etag
        this.id = id
        this.snippet = snippet
        this.player = player
    }
}
