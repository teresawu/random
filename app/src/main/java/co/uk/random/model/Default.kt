package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Default() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()

    @SerializedName("url")
    var url: String = ""
        private set
    @SerializedName("width")
    var width: Int = 0
        private set
    @SerializedName("height")
    var height: Int = 0
        private set

//    @LinkingObjects("default")
//    val thumbnailsDefault: RealmResults<Thumbnails>? = null

    constructor(url: String, width: Int, height: Int) : this() {
        this.url = url
        this.width = width
        this.height = height
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Default) return false

        if (primaryKey != other.primaryKey) return false
        if (url != other.url) return false
        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        return result
    }


}

