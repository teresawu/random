package co.uk.random

data class Receipts(var original: String = "", var total: String = "", var vat: String = "", var type: String = "")

object Keys {
    const val API_KEY_FIREBASE = "AIzaSyAgGrXRlYAM2y77DvGRAF6U-moAj32WvXE"
    const val APP_ID = "1:594350288889:android:ce46f576b5959f7f"
    const val APP_NAME = "random"
}