package co.uk.youtube.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Snippet() : RealmObject() {

    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()

    @SerializedName("publishedAt")
    var publishedAt: String = ""
        private set
    @SerializedName("channelId")
    var channelId: String = ""
        private set
    @SerializedName("title")
    var title: String = ""
        private set
    @SerializedName("description")
    var description: String = ""
        private set
    @SerializedName("thumbnails")
    var thumbnails: co.uk.youtube.model.Thumbnails? = null
        private set
    @SerializedName("channelTitle")
    var channelTitle: String = ""
        private set
    @SerializedName("playlistId")
    var playlistId: String = ""
        private set
    @SerializedName("position")
    var position: Int = 0
        private set
    @SerializedName("resourceId")
    var resourceId: co.uk.youtube.model.ResourceId? = null
        private set
    @SerializedName("localized")
    var localized: co.uk.youtube.model.Localized? = null
        private set

    @LinkingObjects("snippet")
    val itemSnippet: RealmResults<co.uk.youtube.model.Item>? = null

    constructor(publishedAt: String, channelId: String, title: String, description: String, thumbnails: co.uk.youtube.model.Thumbnails?, channelTitle: String, playlistId: String, position: Int, resourceId: co.uk.youtube.model.ResourceId?, localized: co.uk.youtube.model.Localized?) : this() {
        this.publishedAt = publishedAt
        this.channelId = channelId
        this.title = title
        this.description = description
        this.thumbnails = thumbnails
        this.channelTitle = channelTitle
        this.playlistId = playlistId
        this.position = position
        this.resourceId = resourceId
        this.localized = localized
    }
}