package co.uk.room.model


data class ResourceId(
        var kind: String = "",
        var videoId: String = "",
        val snippetResourceId: List<Snippet>? = null)