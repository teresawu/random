package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Video() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 1400

    @SerializedName("kind")
    var kind: String = ""
        private set
    @SerializedName("etag")
    var etag: String = ""
        private set
    @SerializedName("pageInfo")
    var pageInfo: PageInfo? = null
        private set
    @SerializedName("items")
    var items: RealmList<Item> = RealmList()
        private set

    constructor(kind: String, etag: String, pageInfo: PageInfo?, items: RealmList<Item>) : this() {
        this.kind = kind
        this.etag = etag
        this.pageInfo = pageInfo
        this.items = items
    }
}