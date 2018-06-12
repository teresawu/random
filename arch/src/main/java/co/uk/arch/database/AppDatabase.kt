package co.uk.arch.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import co.uk.arch.model.*

@Database(entities = arrayOf(Channel::class, Item::class, Localized::class, PageInfo::class,
        Player::class, Playlist::class, Snippet::class, Thumbnails::class), version = 1, exportSchema = false)
@TypeConverters(AppConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun channelDAO(): ChannelDao
    abstract fun itemDao(): ItemDao
    abstract fun localizedDao(): LocalizedDao
    abstract fun pageInfoDao(): PageInfoDao
    abstract fun playerDao(): PlayerDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun snippetDao(): SnippetDao
    abstract fun thumbnailsDao(): ThumbnailsDao
}