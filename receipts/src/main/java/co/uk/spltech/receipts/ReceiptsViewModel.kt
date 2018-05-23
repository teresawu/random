package co.uk.spltech.receipts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabelDetector
import com.google.firebase.ml.vision.cloud.text.FirebaseVisionCloudTextDetector
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetector
import com.google.firebase.ml.vision.label.FirebaseVisionLabelDetectorOptions
import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ReceiptsViewModel @Inject constructor() {
    val labelDeviceDetector: FirebaseVisionLabelDetector
    val labelCloudDetector: FirebaseVisionCloudLabelDetector
    val textDeviceDetector: FirebaseVisionTextDetector
    val textCloudDetector: FirebaseVisionCloudTextDetector
    lateinit var imageURI: Uri

    init {
        val deviceLabelOptions = FirebaseVisionLabelDetectorOptions.Builder()
                .setConfidenceThreshold(0.8f)
                .build()

        val cloudOption = FirebaseVisionCloudDetectorOptions.Builder()
                .setModelType(FirebaseVisionCloudDetectorOptions.LATEST_MODEL)
                .setMaxResults(15)
                .build()

        labelDeviceDetector = FirebaseVision.getInstance().getVisionLabelDetector(deviceLabelOptions)
        labelCloudDetector = FirebaseVision.getInstance().visionCloudLabelDetector
        textDeviceDetector = FirebaseVision.getInstance().visionTextDetector
        textCloudDetector = FirebaseVision.getInstance().getVisionCloudTextDetector(cloudOption)
    }

    fun uploadIntent(): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        return intent
    }

    fun cameraIntent(context: Context): Intent {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val filephoto = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )
        imageURI = FileProvider.getUriForFile(context, "co.uk.random.provider", filephoto)
        val pictureIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI)
        return pictureIntent
    }
}