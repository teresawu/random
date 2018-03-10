package co.uk.random.model

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
    var default: Default? = null
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
    val snippetThumbnails: RealmResults<Snippet>? = null

    constructor(default: Default?, medium: Medium?, high: High?, standard: Standard?, maxres: Maxres?) : this() {
        this.default = default
        this.medium = medium
        this.high = high
        this.standard = standard
        this.maxres = maxres
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Thumbnails) return false

        if (primaryKey != other.primaryKey) return false
        if (default != other.default) return false
        if (medium != other.medium) return false
        if (high != other.high) return false
        if (standard != other.standard) return false
        if (maxres != other.maxres) return false
        if (snippetThumbnails != other.snippetThumbnails) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey.hashCode()
        result = 31 * result + (default?.hashCode() ?: 0)
        result = 31 * result + (medium?.hashCode() ?: 0)
        result = 31 * result + (high?.hashCode() ?: 0)
        result = 31 * result + (standard?.hashCode() ?: 0)
        result = 31 * result + (maxres?.hashCode() ?: 0)
        result = 31 * result + (snippetThumbnails?.hashCode() ?: 0)
        return result
    }


}