package co.uk.room.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import co.uk.room.model.*

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
interface DefaultDao {
    @Query("SELECT * FROM `default`")
    fun getAllDefault(): LiveData<List<Default>>

    @Insert(onConflict = REPLACE)
    fun insert(default: Default)

    @Update(onConflict = REPLACE)
    fun update(default: Default)

    @Delete
    fun delete(default: Default)
}

@Dao
interface HighDao {
    @Query("SELECT * FROM High")
    fun getAllHigh(): LiveData<List<High>>

    @Insert(onConflict = REPLACE)
    fun insert(high: High)

    @Update(onConflict = REPLACE)
    fun update(high: High)

    @Delete
    fun delete(high: High)
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
interface MaxresDao {
    @Query("SELECT * FROM maxres")
    fun getAllMaxres(): LiveData<List<Maxres>>

    @Insert(onConflict = REPLACE)
    fun insert(maxres: Maxres)

    @Update(onConflict = REPLACE)
    fun update(maxres: Maxres)

    @Delete
    fun delete(maxres: Maxres)
}

@Dao
interface MediumDao {
    @Query("SELECT * FROM medium")
    fun getAllMedium(): LiveData<List<Medium>>

    @Insert(onConflict = REPLACE)
    fun insert(medium: Medium)

    @Update(onConflict = REPLACE)
    fun update(medium: Medium)

    @Delete
    fun delete(medium: Medium)
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
interface ResourceIdDao {
    @Query("SELECT * FROM resourceId")
    fun getAllResourceId(): LiveData<List<ResourceId>>

    @Insert(onConflict = REPLACE)
    fun insert(resourceId: ResourceId)

    @Update(onConflict = REPLACE)
    fun update(resourceId: ResourceId)

    @Delete
    fun delete(resourceId: ResourceId)
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
interface StandardDao {
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

@Dao
interface VideoDao {
    @Query("SELECT * FROM video")
    fun getAllVideo(): LiveData<List<Video>>

    @Insert(onConflict = REPLACE)
    fun insert(video: Video)

    @Update(onConflict = REPLACE)
    fun update(video: Video)

    @Delete
    fun delete(video: Video)
}