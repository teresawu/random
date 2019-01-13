package co.uk.mlkit

import android.content.Intent
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabelDetector
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetectorOptions
import com.google.firebase.ml.vision.text.FirebaseVisionCloudTextRecognizerOptions
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import javax.inject.Inject


class LabelViewModel @Inject constructor() {
    val labelDeviceDetector: FirebaseVisionLabelDetector
    val labelCloudDetector: FirebaseVisionCloudLabelDetector
    val textDeviceDetector: FirebaseVisionTextRecognizer
    val textCloudDetector: FirebaseVisionTextRecognizer

    init {
        val deviceLabelOptions = FirebaseVisionLabelDetectorOptions.Builder()
                .setConfidenceThreshold(0.8f)
                .build()

        val cloudOption = FirebaseVisionCloudTextRecognizerOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .build()

        labelDeviceDetector = FirebaseVision.getInstance().getVisionLabelDetector(deviceLabelOptions)
        labelCloudDetector = FirebaseVision.getInstance().visionCloudLabelDetector
        textDeviceDetector = FirebaseVision.getInstance().getCloudTextRecognizer()
        textCloudDetector = FirebaseVision.getInstance().getCloudTextRecognizer(cloudOption)
    }

    fun imageIntent(): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        return intent
    }
}