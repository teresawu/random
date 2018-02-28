package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
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

    constructor(title: String, description: String) : this() {
        this.title = title
        this.description = description
    }
}