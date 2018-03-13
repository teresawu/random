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
    @LinkingObjects("resourceId")
    val snippetResourceId: RealmResults<Snippet>? = null

    constructor(kind: String, videoId: String) : this() {
        this.kind = kind
        this.videoId = videoId
    }
}