package co.uk.room.model

import java.util.*

data class Playlist(
        var kind: String = "",
        var etag: String = "",
        var pageInfo: PageInfo? = null,
        var items: List<Item> = ArrayList())
