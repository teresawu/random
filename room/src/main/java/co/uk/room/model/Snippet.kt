package co.uk.room.model

data class Snippet(
        var publishedAt: String = "",
        var channelId: String = "",
        var title: String = "",
        var description: String = "",
        var thumbnails: Thumbnails? = null,
        var channelTitle: String = "",
        var playlistId: String = "",
        var position: Int = 0,
        var resourceId: ResourceId? = null,
        var localized: Localized? = null,
        val itemSnippet: List<Item>? = null)