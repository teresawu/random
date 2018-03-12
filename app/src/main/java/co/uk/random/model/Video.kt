//package co.uk.random.model
//
//import com.google.gson.annotations.SerializedName
//import io.realm.RealmList
//import io.realm.RealmObject
//import io.realm.annotations.PrimaryKey
//import java.util.*
//
//open class Video() : RealmObject() {
//    @PrimaryKey
//    var primaryKey = UUID.randomUUID().toString()
//
//    @SerializedName("kind")
//    var kind: String = ""
//        private set
//    @SerializedName("etag")
//    var etag: String = ""
//        private set
//    @SerializedName("pageInfo")
//    var pageInfo: PageInfo? = null
//        private set
//    @SerializedName("items")
//    var items: RealmList<Item> = RealmList()
//        private set
//
//    constructor(kind: String, etag: String, pageInfo: PageInfo?, items: RealmList<Item>) : this() {
//        this.kind = kind
//        this.etag = etag
//        this.pageInfo = pageInfo
//        this.items = items
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other !is Video) return false
//
//        if (primaryKey != other.primaryKey) return false
//        if (kind != other.kind) return false
//        if (etag != other.etag) return false
//        if (pageInfo != other.pageInfo) return false
//        if (items != other.items) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = primaryKey.hashCode()
//        result = 31 * result + kind.hashCode()
//        result = 31 * result + etag.hashCode()
//        result = 31 * result + (pageInfo?.hashCode() ?: 0)
//        result = 31 * result + items.hashCode()
//        return result
//    }
//
//
//}