package co.uk.spltech.receipts

import java.util.*

fun String.findLargestFloat(): String {
    //get digits from result
    val matchedResults = Regex(pattern = "[+-]?([0-9]*[.])?[0-9]+").findAll(this)
    val originalResult = ArrayList<Float>()
    for (txt in matchedResults) {
        if (txt.value.isFloatAndWhole()) originalResult.add(txt.value.toFloat())
    }
    return Collections.max(originalResult).toString()
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