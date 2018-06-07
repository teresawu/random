package co.uk.room.model

data class Maxres(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        val thumbnailsMaxres: List<Thumbnails>? = null)
