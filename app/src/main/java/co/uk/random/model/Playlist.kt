package co.uk.random.model
import com.google.gson.annotations.SerializedName


data class Playlist(
		@SerializedName("kind") val kind: String = "",
		@SerializedName("etag") val etag: String = "",
		@SerializedName("pageInfo") val pageInfo: PageInfo = PageInfo(),
		@SerializedName("items") val items: List<Item> = listOf()
)

data class ResourceId(
		@SerializedName("kind") val kind: String = "",
		@SerializedName("videoId") val videoId: String = ""
)