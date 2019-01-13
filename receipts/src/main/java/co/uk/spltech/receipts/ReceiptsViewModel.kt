package co.uk.spltech.receipts

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class ReceiptsViewModel @Inject constructor() {
    val textDeviceDetector: FirebaseVisionTextRecognizer
    lateinit var imageURI: Uri

    init {
        textDeviceDetector = FirebaseVision.getInstance().getOnDeviceTextRecognizer()
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

    fun getReceipts(text: String): Receipts {
        val originalResult = text.findFloat()
        if (originalResult.isEmpty()) return Receipts()
        else {
            val receipts = Receipts()
            val totalF = Collections.max(originalResult)
            val secondLargestF = findSecondLargestFloat(originalResult)
            receipts.total = totalF.toString()
            receipts.vat = if (secondLargestF == 0.0f) "0" else "%.2f".format(totalF - secondLargestF)
            receipts.type = text.firstLine()
            return receipts
        }
    }

    private fun findSecondLargestFloat(input: ArrayList<Float>?): Float {
        if (input == null || input.isEmpty() || input.size == 1) return 0.0f
        else {
            try {
                val tempSet = HashSet(input)
                val sortedSet = TreeSet(tempSet)
                return sortedSet.elementAt(sortedSet.size - 2)
            } catch (e: Exception) {
                return 0.0f
            }
        }
    }
}