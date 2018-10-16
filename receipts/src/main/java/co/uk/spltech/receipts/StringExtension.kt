package co.uk.spltech.receipts

import java.util.*

fun String.findFloat(): ArrayList<Float> {
    //get digits from result
    if (this.isEmpty()) return ArrayList<Float>()
    val originalResult = ArrayList<Float>()
    val matchedResults = Regex(pattern = "[+-]?([0-9]*[.])?[0-9]+").findAll(this)
    for (txt in matchedResults) {
        if (txt.value.isFloatAndWhole()) originalResult.add(txt.value.toFloat())
    }
    return originalResult
}

fun String.firstLine(): String {
    if (this.isEmpty()) return ""
    return this.split("\n").get(0)
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