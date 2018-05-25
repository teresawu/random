package co.uk.youtube.util.extension

import com.google.gson.GsonBuilder

/**
 * Extension functions for Strings.
 */

/**
 * Parses a JSON string into a map of key-value pairs.
 */
@Suppress("UNCHECKED_CAST")
fun String.parseJSONToMap(): Map<String, Any?> {
    val gson = GsonBuilder().create()
    val map = gson.fromJson(this, Map::class.java)
    return map as Map<String, Any?>
}