package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class High() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 1800

    @SerializedName("url")
    var url: String = ""
        private set
    @SerializedName("width")
    var width: Int = 0
        private set
    @SerializedName("height")
    var height: Int = 0
        private set
    @LinkingObjects("high") val channel: RealmResults<Thumbnails>? = null
    constructor(url: String, width: Int, height: Int) : this() {
        this.url = url
        this.width = width
        this.height = height
    }
}