package co.uk.mlkit

import android.graphics.Bitmap
import android.support.annotation.NonNull
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabelDetector
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetectorOptions
import io.reactivex.Single
import javax.inject.Inject


class LabelViewModel @Inject constructor() {
    //By default the on-device image labeler returns at most 10 labels
    private val deviceOptions: FirebaseVisionLabelDetectorOptions
    private val deviceDetector: FirebaseVisionLabelDetector
    private val firebaseDetector: FirebaseVisionCloudLabelDetector
    private val cloudLabelList = ArrayList<LabelItem>()
    private val deviceLabelList = ArrayList<LabelItem>()

    init {
        deviceOptions = FirebaseVisionLabelDetectorOptions.Builder()
                .setConfidenceThreshold(0.8f)
                .build()
        deviceDetector = FirebaseVision.getInstance().getVisionLabelDetector(deviceOptions)
        firebaseDetector = FirebaseVision.getInstance().visionCloudLabelDetector
    }

    fun detectObject(bitmap: Bitmap): Single<List<LabelItem>> {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        deviceLabelList.clear()
        return Single.just(bitmap).flatMap {
            deviceDetector.detectInImage(image)
                    .addOnSuccessListener {
                        for (label in it) {
                            deviceLabelList.add(LabelItem(label.getLabel(), label.getEntityId(), label.getConfidence()))
                            val text = label.getLabel()
                            val entityId = label.getEntityId()
                            val confidence = label.getConfidence()
                            Log.i("image label", "text " + text + " //entityId " + entityId + " //confidence " + confidence)
                        }
                    }
                    .addOnFailureListener(
                            object : OnFailureListener {
                                override fun onFailure(@NonNull e: Exception) {
                                    Log.i("image label", "opps! failed on device")
                                }
                            })
            return@flatMap Single.just(deviceLabelList)
        }
    }

    fun detectObjectOnCloud(bitmap: Bitmap): Single<List<LabelItem>> {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        cloudLabelList.clear()
        return Single.just(bitmap).flatMap {
            firebaseDetector.detectInImage(image)
                    .addOnSuccessListener {
                        for (label in it) {
                            cloudLabelList.add(LabelItem(label.getLabel(), label.getEntityId(), label.getConfidence()))
                            val text = label.getLabel()
                            val entityId = label.getEntityId()
                            val confidence = label.getConfidence()
                            Log.i("image label", "text " + text + " //entityId " + entityId + " //confidence " + confidence)
                        }
                    }
                    .addOnFailureListener(
                            object : OnFailureListener {
                                override fun onFailure(@NonNull e: Exception) {
                                    Log.i("image label", "opps! failed on cloud")
                                }
                            })
            return@flatMap Single.just(deviceLabelList)
        }
    }
}