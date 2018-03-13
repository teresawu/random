package co.uk.random.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.util.*

open class Player() : RealmObject() {
    @PrimaryKey
    var primaryKey = UUID.randomUUID().toString()
    @SerializedName("embedHtml")
    var embedHtml: String = ""
        private set

    @LinkingObjects("player")
    val itemPlayer: RealmResults<Item>? = null

    constructor(embedHtml: String) : this() {
        this.embedHtml = embedHtml
    }
}