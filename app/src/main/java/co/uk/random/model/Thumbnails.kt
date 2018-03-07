package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class Thumbnails() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 2400

    @SerializedName("default")
    var default: Default = Default()
        private set
    @SerializedName("medium")
    var medium: Medium? = null
        private set
    @SerializedName("high")
    var high: High? = null
        private set
    @SerializedName("standard")
    var standard: Standard? = null
        private set
    @SerializedName("maxres")
    var maxres: Maxres? = null
        private set

    @LinkingObjects("thumbnails")
    val snippet: RealmResults<Snippet>? = null

    constructor(default: Default, medium: Medium?, high: High?, standard: Standard?, maxres: Maxres?) : this() {
        this.default = default
        this.medium = medium
        this.high = high
        this.standard = standard
        this.maxres = maxres
    }
}