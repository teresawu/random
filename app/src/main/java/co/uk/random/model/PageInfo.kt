package co.uk.random.model

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
    val channelPageInfo: RealmResults<Channel>? = null
    @LinkingObjects("pageInfo")
    val playlistPageInfo: RealmResults<Playlist>? = null

    constructor(totalResults: Int, resultsPerPage: Int) : this() {
        this.totalResults = totalResults
        this.resultsPerPage = resultsPerPage
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PageInfo) return false

        if (primaryKey != other.primaryKey) return false
        if (totalResults != other.totalResults) return false
        if (resultsPerPage != other.resultsPerPage) return false
        if (channelPageInfo != other.channelPageInfo) return false
        if (playlistPageInfo != other.playlistPageInfo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey.hashCode()
        result = 31 * result + totalResults
        result = 31 * result + resultsPerPage
        result = 31 * result + (channelPageInfo?.hashCode() ?: 0)
        result = 31 * result + (playlistPageInfo?.hashCode() ?: 0)
        return result
    }


}