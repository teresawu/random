package co.uk.random.model

import com.google.gson.annotations.SerializedName

data class Channel(
        @SerializedName("kind") val kind: String = "",
        @SerializedName("etag") val etag: String = "",
        @SerializedName("nextPageToken") val nextPageToken: String = "",
        @SerializedName("pageInfo") val pageInfo: PageInfo = PageInfo(),
        @SerializedName("items") val items: List<Item> = listOf()
)

data class Item(
        @SerializedName("kind") val kind: String = "",
        @SerializedName("etag") val etag: String = "",
        @SerializedName("id") val id: String = "",
        @SerializedName("snippet") val snippet: Snippet = Snippet(),
        @SerializedName("player") val player: Player = Player()
)

data class Snippet(
        @SerializedName("publishedAt") val publishedAt: String = "",
        @SerializedName("channelId") val channelId: String = "",
        @SerializedName("title") val title: String = "",
        @SerializedName("description") val description: String = "",
        @SerializedName("thumbnails") val thumbnails: Thumbnails = Thumbnails(),
        @SerializedName("channelTitle") val channelTitle: String = "",
        @SerializedName("playlistId") val playlistId: String = "",
        @SerializedName("position") val position: Int = 0,
        @SerializedName("resourceId") val resourceId: ResourceId = ResourceId(),
        @SerializedName("localized") val localized: Localized = Localized()
)


data class Localized(
        @SerializedName("title") val title: String = "",
        @SerializedName("description") val description: String = ""
)

data class Thumbnails(
        @SerializedName("default") val default: Default = Default(),
        @SerializedName("medium") val medium: Medium = Medium(),
        @SerializedName("high") val high: High = High(),
        @SerializedName("standard") val standard: Standard = Standard(),
        @SerializedName("maxres") val maxres: Maxres = Maxres()
)

data class Maxres(
        @SerializedName("url") val url: String = "",
        @SerializedName("width") val width: Int = 0,
        @SerializedName("height") val height: Int = 0
)

data class Medium(
        @SerializedName("url") val url: String = "",
        @SerializedName("width") val width: Int = 0,
        @SerializedName("height") val height: Int = 0
)

data class Default(
        @SerializedName("url") val url: String = "",
        @SerializedName("width") val width: Int = 0,
        @SerializedName("height") val height: Int = 0
)

data class Standard(
        @SerializedName("url") val url: String = "",
        @SerializedName("width") val width: Int = 0,
        @SerializedName("height") val height: Int = 0
)

data class High(
        @SerializedName("url") val url: String = "",
        @SerializedName("width") val width: Int = 0,
        @SerializedName("height") val height: Int = 0
)

data class PageInfo(
        @SerializedName("totalResults") val totalResults: Int = 0,
        @SerializedName("resultsPerPage") val resultsPerPage: Int = 0
)