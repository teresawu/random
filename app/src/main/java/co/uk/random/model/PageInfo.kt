package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PageInfo() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 2200

    @SerializedName("totalResults")
    var totalResults: Int = 0
        private set
    @SerializedName("resultsPerPage")
    var resultsPerPage: Int = 0
        private set

    constructor(totalResults: Int, resultsPerPage: Int) : this() {
        this.totalResults = totalResults
        this.resultsPerPage = resultsPerPage
    }
}