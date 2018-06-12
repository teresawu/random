package co.uk.arch.database

import android.arch.persistence.room.TypeConverter
import co.uk.arch.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppConverter {
    @TypeConverter
    fun stringToChannels(json: String): List<Channel> {
        val type = object : TypeToken<List<Channel>>() {}.getType()
        return Gson().fromJson<List<Channel>>(json, type)
    }

    @TypeConverter
    fun channelToString(list: List<Channel>): String {
        val type = object : TypeToken<List<Channel>>() {}.getType()
        return Gson().toJson(list, type)
    }
}