package co.uk.room.database

import android.arch.persistence.room.TypeConverter
import co.uk.room.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AppConverter {
    @TypeConverter
    fun stringToItem(json: String): List<Item> {
        val type = object : TypeToken<List<Item>>() {}.getType()
        return Gson().fromJson<List<Item>>(json, type)
    }

    @TypeConverter
    fun itemToString(list: List<Item>): String {
        val type = object : TypeToken<List<Item>>() {}.getType()
        return Gson().toJson(list, type)
    }

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

    @TypeConverter
    fun stringToPlaylist(json: String): List<Playlist> {
        val type = object : TypeToken<List<Playlist>>() {}.getType()
        return Gson().fromJson<List<Playlist>>(json, type)
    }

    @TypeConverter
    fun playlistToString(list: List<Playlist>): String {
        val type = object : TypeToken<List<Playlist>>() {}.getType()
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun stringToSnippet(json: String): List<Snippet> {
        val type = object : TypeToken<List<Snippet>>() {}.getType()
        return Gson().fromJson<List<Snippet>>(json, type)
    }

    @TypeConverter
    fun snippetToString(list: List<Snippet>): String {
        val type = object : TypeToken<List<Snippet>>() {}.getType()
        return Gson().toJson(list, type)
    }

    @TypeConverter
    fun stringToThumbnails(json: String): List<Thumbnails> {
        val type = object : TypeToken<List<Thumbnails>>() {}.getType()
        return Gson().fromJson<List<Thumbnails>>(json, type)
    }

    @TypeConverter
    fun thumbnailsToString(list: List<Thumbnails>): String {
        val type = object : TypeToken<List<Thumbnails>>() {}.getType()
        return Gson().toJson(list, type)
    }
}