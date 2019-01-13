package co.uk.youtube.model

data class Video(
        val firstName: String,
        val lastName: String,
        val pictureUrl: String?) {
    constructor(video: Video) : this(
            video.firstName,
            video.lastName,
            if (video.pictureUrl.equals("")) null else video.pictureUrl)

    init {

    }
}