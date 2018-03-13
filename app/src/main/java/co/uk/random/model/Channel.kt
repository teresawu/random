package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Channel() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()

    @SerializedName("name")
    var name: String = ""
        private set
    @SerializedName("kind")
    var kind: String = ""
        private set
    @SerializedName("etag")
    var etag: String = ""
        private set
    @SerializedName("nextPageToken")
    var nextPageToken: String = ""
        private set
    @SerializedName("pageInfo")
    var pageInfo: PageInfo? = null
        private set
    @SerializedName("items")
    var items: RealmList<Item> = RealmList()
        private set

    constructor(name: String, kind: String, etag: String, nextPageToken: String, pageInfo: PageInfo?, items: RealmList<Item>) : this() {
        this.etag = etag
        this.name = name
        this.kind = kind
        this.nextPageToken = nextPageToken
        this.pageInfo = pageInfo
        this.items = items
    }
}