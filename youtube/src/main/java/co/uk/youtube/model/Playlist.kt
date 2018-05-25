package co.uk.youtube.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


open class Playlist() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()

    @SerializedName("kind")
    var kind: String = ""
        private set
    @SerializedName("etag")
    var etag: String = ""
        private set
    @SerializedName("pageInfo")
    var pageInfo: co.uk.youtube.model.PageInfo? = null
        private set
    @SerializedName("items")
    var items: RealmList<co.uk.youtube.model.Item> = RealmList()
        private set

    constructor(kind: String, etag: String, pageInfo: co.uk.youtube.model.PageInfo?, items: RealmList<co.uk.youtube.model.Item>) : this() {
        this.kind = kind
        this.etag = etag
        this.pageInfo = pageInfo
        this.items = items
    }
}
