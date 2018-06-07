package co.uk.room.model

data class Item(
        var kind: String = "",
        var etag: String = "",
        var id: String = "",
        var snippet: Snippet? = null,
        var player: Player? = null,
        val channelItem: List<Channel>? = null,
        val playlistItem: List<Playlist>? = null)