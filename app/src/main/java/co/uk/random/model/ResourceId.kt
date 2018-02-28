package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class ResourceId() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 1300
    @SerializedName("kind")
    var kind: String = ""
        private set
    @SerializedName("videoId")
    var videoId: String = ""
        private set

    constructor(kind: String, videoId: String) : this() {
        this.kind = kind
        this.videoId = videoId
    }
}