package co.uk.room.model

data class Channel(var name: String = "", var kind: String = "", var etag: String = "", var nextPageToken: String = "",
                   var pageInfo: PageInfo? = null, var items: List<Item> = ArrayList())