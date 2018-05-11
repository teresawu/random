package co.uk.mlkit

import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabelDetector
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetectorOptions
import javax.inject.Inject


class LabelViewModel @Inject constructor() {
    //By default the on-device image labeler returns at most 10 labels
    private val deviceOptions: FirebaseVisionLabelDetectorOptions
    val deviceDetector: FirebaseVisionLabelDetector
    val firebaseDetector: FirebaseVisionCloudLabelDetector

    init {
        deviceOptions = FirebaseVisionLabelDetectorOptions.Builder()
                .setConfidenceThreshold(0.8f)
                .build()
        deviceDetector = FirebaseVision.getInstance().getVisionLabelDetector(deviceOptions)
        firebaseDetector = FirebaseVision.getInstance().visionCloudLabelDetector
    }
}