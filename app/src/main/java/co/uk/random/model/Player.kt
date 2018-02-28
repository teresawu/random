package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Player() : RealmObject() {
    @PrimaryKey
    var primaryKey = System.currentTimeMillis() / 1500
    @SerializedName("embedHtml")
    var embedHtml: String = ""
        private set

    constructor(embedHtml: String) : this() {
        this.embedHtml = embedHtml
    }
}