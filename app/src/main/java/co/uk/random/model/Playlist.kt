package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class Playlist() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 1000

    @SerializedName("kind")
    var kind: String = ""
        private set
    @SerializedName("etag")
    var etag: String = ""
        private set
    @SerializedName("pageInfo")
    var pageInfo: PageInfo = PageInfo()
        private set
    @SerializedName("items")
    var items: List<Item> = listOf()
        private set

    constructor(kind: String, etag: String, pageInfo: PageInfo, items: List<Item>) : this() {
        this.kind = kind
        this.etag = etag
        this.pageInfo = pageInfo
        this.items = items
    }
}

data class ResourceId(
        @SerializedName("kind") val kind: String = "",
        @SerializedName("videoId") val videoId: String = ""
)