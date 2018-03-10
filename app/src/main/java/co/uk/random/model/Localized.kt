package co.uk.random.model

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
    val snippetLocalized: RealmResults<Snippet>? = null

    constructor(title: String, description: String) : this() {
        this.title = title
        this.description = description
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Localized) return false

        if (primaryKey != other.primaryKey) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (snippetLocalized != other.snippetLocalized) return false

        return true
    }

    override fun hashCode(): Int {
        var result = primaryKey.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + (snippetLocalized?.hashCode() ?: 0)
        return result
    }


}