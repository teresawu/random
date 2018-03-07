package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class Localized() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 1900

    @SerializedName("title")
    var title: String = ""
        private set
    @SerializedName("description")
    var description: String = ""
        private set
    @LinkingObjects("localized")
    val channel: RealmResults<Snippet>? = null

    constructor(title: String, description: String) : this() {
        this.title = title
        this.description = description
    }
}