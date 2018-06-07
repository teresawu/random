package co.uk.room.model

data class High(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        val thumbnailsHigh: List<Thumbnails>? = null)