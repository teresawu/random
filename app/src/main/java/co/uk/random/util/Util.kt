package co.uk.random.util

import co.uk.random.model.Item
import co.uk.random.model.Video
import io.realm.RealmList

object Util {
    fun shortDescription(str: String?): String {
        return if (str == null || str.isEmpty())
            "Learn more about integrating your own hardware devices with the Google Assistant. This video series covers how to embed the Google Assistant SDK into custom hardware projects"
        else {
            str.split("\\r?\\n".toRegex())[0]
        }
    }

    fun getMockVideo(item: Item?): Video {
        val items = RealmList<Item>()
        items.add(item)
        return Video("", "", null, items)
    }
}
