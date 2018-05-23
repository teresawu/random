package co.uk.spltech.receipts

import java.util.*

fun String.findLargestFloat(): String {
    //get digits from result
    if (this == null || this.isEmpty()) return ""
    val originalResult = ArrayList<Float>()
    val matchedResults = Regex(pattern = "[+-]?([0-9]*[.])?[0-9]+").findAll(this)
    if (matchedResults != null)
        for (txt in matchedResults) {
            if (txt.value.isFloatAndWhole()) originalResult.add(txt.value.toFloat())
        }
    return if (originalResult.isNotEmpty()) Collections.max(originalResult).toString()
    else return ""
}

private fun String.isFloatAndWhole() = this.matches("\\d*\\.\\d*".toRegex())

private fun String.isFloat(): Boolean {
    try {
        val d = java.lang.Double.valueOf(this)
        return d != d.toInt().toDouble()
    } catch (e: Exception) {
        return false
    }
}