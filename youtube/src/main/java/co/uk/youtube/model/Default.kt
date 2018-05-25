package co.uk.youtube.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
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
}

