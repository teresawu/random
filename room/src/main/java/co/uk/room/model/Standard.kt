package co.uk.room.model

data class Standard(
        var url: String = "",
        var width: Int = 0,
        var height: Int = 0,
        val thumbnailsStandard: List<Thumbnails>? = null)