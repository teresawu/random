package co.uk.youtube.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Maxres() : RealmObject() {
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
    @LinkingObjects("maxres")
    val thumbnailsMaxres: RealmResults<co.uk.youtube.model.Thumbnails>? = null

    constructor(url: String, width: Int, height: Int) : this() {
        this.url = url
        this.width = width
        this.height = height
    }
}
