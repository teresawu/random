package co.uk.arch.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import co.uk.arch.model.*

@Database(entities = arrayOf(Channel::class, Default::class, High::class,
        Item::class, Localized::class, Maxres::class, Medium::class, PageInfo::class,
        Player::class, Playlist::class, ResourceId::class, Snippet::class, Standard::class,
        Thumbnails::class, Video::class), version = 1, exportSchema = false)
@TypeConverters(AppConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun channelDAO(): ChannelDao
    abstract fun defaultDAO(): DefaultDao
    abstract fun highDao(): HighDao
    abstract fun itemDao(): ItemDao
    abstract fun localizedDao(): LocalizedDao
    abstract fun maxresDao(): MaxresDao
    abstract fun mediumDao(): MediumDao
    abstract fun pageInfoDao(): PageInfoDao
    abstract fun playerDao(): PlayerDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun resourceIdDao(): ResourceIdDao
    abstract fun snippetDao(): SnippetDao
    abstract fun standardDao(): StandardDao
    abstract fun thumbnailsDao(): ThumbnailsDao
    abstract fun videoDao(): VideoDao

}