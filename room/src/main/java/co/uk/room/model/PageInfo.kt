package co.uk.room.model

data class PageInfo(
        var totalResults: Int = 0,
        var resultsPerPage: Int = 0,
        val channelPageInfo: List<Channel>? = null,
        val playlistPageInfo: List<Playlist>? = null)