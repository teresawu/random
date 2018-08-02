package co.uk.youtube.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class PageInfo() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()

    @SerializedName("totalResults")
    var totalResults: Int = 0
        private set
    @SerializedName("resultsPerPage")
    var resultsPerPage: Int = 0
        private set

    @LinkingObjects("pageInfo")
    val channelPageInfo: RealmResults<co.uk.youtube.model.Channel>? = null
    @LinkingObjects("pageInfo")
    val playlistPageInfo: RealmResults<co.uk.youtube.model.Playlist>? = null

    constructor(totalResults: Int, resultsPerPage: Int) : this() {
        this.totalResults = totalResults
        this.resultsPerPage = resultsPerPage
    }
}