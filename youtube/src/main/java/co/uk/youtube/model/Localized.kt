package co.uk.youtube.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Localized() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()

    @SerializedName("title")
    var title: String = ""
        private set
    @SerializedName("description")
    var description: String = ""
        private set
    @LinkingObjects("localized")
    val snippetLocalized: RealmResults<co.uk.youtube.model.Snippet>? = null

    constructor(title: String, description: String) : this() {
        this.title = title
        this.description = description
    }
}