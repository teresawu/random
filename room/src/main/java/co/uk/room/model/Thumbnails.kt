package co.uk.room.model

data class Thumbnails(
        var default: Default? = null,
        var medium: Medium? = null,
        var high: High? = null,
        var standard: Standard? = null,
        var maxres: Maxres? = null,
        val snippetThumbnails: List<Snippet>? = null)