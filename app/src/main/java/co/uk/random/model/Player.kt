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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (primaryKey != other.primaryKey) return false
        if (embedHtml != other.embedHtml) return false
        if (itemPlayer != other.itemPlayer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey.hashCode()
        result = 31 * result + embedHtml.hashCode()
        result = 31 * result + (itemPlayer?.hashCode() ?: 0)
        return result
    }


}