package co.uk.arch.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import co.uk.arch.model.*

@Dao
interface ChannelDao {
    @Query("SELECT * FROM channel")
    fun getAllChannel(): LiveData<List<Channel>>

    @Insert(onConflict = REPLACE)
    fun insert(channel: Channel)

    @Update(onConflict = REPLACE)
    fun update(channel: Channel)

    @Delete
    fun delete(channel: Channel)
}

@Dao
interface ItemDao {
    @Query("SELECT * FROM item")
    fun getAllItem(): LiveData<List<Item>>

    @Insert(onConflict = REPLACE)
    fun insert(item: Item)

    @Update(onConflict = REPLACE)
    fun update(item: Item)

    @Delete
    fun delete(item: Item)
}

@Dao
interface LocalizedDao {
    @Query("SELECT * FROM localized")
    fun getAllLocalized(): LiveData<List<Localized>>

    @Insert(onConflict = REPLACE)
    fun insert(localized: Localized)

    @Update(onConflict = REPLACE)
    fun update(localized: Localized)

    @Delete
    fun delete(localized: Localized)
}

@Dao
interface PageInfoDao {
    @Query("SELECT * FROM pageInfo")
    fun getAllPageInfo(): LiveData<List<PageInfo>>

    @Insert(onConflict = REPLACE)
    fun insert(pageInfo: PageInfo)

    @Update(onConflict = REPLACE)
    fun update(pageInfo: PageInfo)

    @Delete
    fun delete(pageInfo: PageInfo)
}

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAllPlayer(): LiveData<List<Player>>

    @Insert(onConflict = REPLACE)
    fun insert(player: Player)

    @Update(onConflict = REPLACE)
    fun update(player: Player)

    @Delete
    fun delete(player: Player)
}

@Dao
interface PlaylistDao {
    @Query("SELECT * FROM playlist")
    fun getAllPlaylist(): LiveData<List<Playlist>>

    @Insert(onConflict = REPLACE)
    fun insert(playlist: Playlist)

    @Update(onConflict = REPLACE)
    fun update(playlist: Playlist)

    @Delete
    fun delete(playlist: Playlist)
}

@Dao
interface SnippetDao {
    @Query("SELECT * FROM snippet")
    fun getAllSnippet(): LiveData<List<Snippet>>

    @Insert(onConflict = REPLACE)
    fun insert(snippet: Snippet)

    @Update(onConflict = REPLACE)
    fun update(snippet: Snippet)

    @Delete
    fun delete(snippet: Snippet)
}

@Dao
interface ThumbnailsDao {
    @Query("SELECT * FROM thumbnails")
    fun getAllThumbnails(): LiveData<List<Thumbnails>>

    @Insert(onConflict = REPLACE)
    fun insert(thumbnails: Thumbnails)

    @Update(onConflict = REPLACE)
    fun update(thumbnails: Thumbnails)

    @Delete
    fun delete(thumbnails: Thumbnails)
}