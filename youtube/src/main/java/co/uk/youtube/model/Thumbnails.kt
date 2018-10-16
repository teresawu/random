package co.uk.youtube.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Thumbnails() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()

    @SerializedName("default")
    var default: co.uk.youtube.model.Default? = null
        private set
    @SerializedName("medium")
    var medium: co.uk.youtube.model.Medium? = null
        private set
    @SerializedName("high")
    var high: co.uk.youtube.model.High? = null
        private set
    @SerializedName("standard")
    var standard: co.uk.youtube.model.Standard? = null
        private set
    @SerializedName("maxres")
    var maxres: co.uk.youtube.model.Maxres? = null
        private set

    @LinkingObjects("thumbnails")
    val snippetThumbnails: RealmResults<co.uk.youtube.model.Snippet>? = null

    constructor(default: co.uk.youtube.model.Default?, medium: co.uk.youtube.model.Medium?, high: co.uk.youtube.model.High?, standard: co.uk.youtube.model.Standard?, maxres: co.uk.youtube.model.Maxres?) : this() {
        this.default = default
        this.medium = medium
        this.high = high
        this.standard = standard
        this.maxres = maxres
    }
}