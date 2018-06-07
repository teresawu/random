package co.uk.room.model

data class Medium(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        val thumbnailsMedium: List<Thumbnails>? = null)