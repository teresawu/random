package co.uk.room.model

data class Localized(
        var title: String = "",
        var description: String = "",
        val snippetLocalized: List<Snippet>? = null)