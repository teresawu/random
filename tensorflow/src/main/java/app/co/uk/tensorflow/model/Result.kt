package app.co.uk.tensorflow.model

import android.graphics.RectF

class Result(val id: String?, val title: String?, val confidence: Float?, private var location: RectF?) {
    override fun toString(): String {
        var resultString = ""
        if (title != null) resultString += title + ": "
        if (confidence != null) resultString += confidence.toString() 
        return resultString
    }
}