package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*


open class ResourceId() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()
    @SerializedName("kind")
    var kind: String = ""
        private set
    @SerializedName("videoId")
    var videoId: String = ""
        private set
    @LinkingObjects("resourceId") val snippetResourceId: RealmResults<Snippet>? = null

    constructor(kind: String, videoId: String) : this() {
        this.kind = kind
        this.videoId = videoId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ResourceId) return false

        if (primaryKey != other.primaryKey) return false
        if (kind != other.kind) return false
        if (videoId != other.videoId) return false
        if (snippetResourceId != other.snippetResourceId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey.hashCode()
        result = 31 * result + kind.hashCode()
        result = 31 * result + videoId.hashCode()
        result = 31 * result + (snippetResourceId?.hashCode() ?: 0)
        return result
    }


}