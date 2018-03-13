package co.uk.random.model

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
    var thumbnails: Thumbnails? = null
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
    var resourceId: ResourceId? = null
        private set
    @SerializedName("localized")
    var localized: Localized? = null
        private set

    @LinkingObjects("snippet")
    val itemSnippet: RealmResults<Item>? = null

    constructor(publishedAt: String, channelId: String, title: String, description: String, thumbnails: Thumbnails?, channelTitle: String, playlistId: String, position: Int, resourceId: ResourceId?, localized: Localized?) : this() {
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