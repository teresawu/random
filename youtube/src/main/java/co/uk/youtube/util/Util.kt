package co.uk.youtube.util

object Util {
    fun shortDescription(str: String?): String {
        return if (str == null || str.isEmpty())
            "Learn more about integrating your own hardware devices with the Google Assistant. This video series covers how to embed the Google Assistant SDK into custom hardware projects"
        else {
            str.split("\\r?\\n".toRegex())[0]
        }
    }
}
