package co.uk.mlkit

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_mlkit.*
import java.io.FileNotFoundException
import javax.inject.Inject


class MLKitActivity : DaggerAppCompatActivity() {
    private val IMAGE_LABEL = 1001
    private val TEXT_RECOGNITION = 1002
    private val SMART_REPLY = 1003
    private var action = IMAGE_LABEL
    private lateinit var photoImage: Bitmap
    private lateinit var firebaseImage: FirebaseVisionImage
    @Inject
    lateinit var labelViewModel: LabelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlkit)
        //check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
        setUI()
    }

    private fun setUI() {
        imageResult.setOnClickListener {
            startActivityForResult(labelViewModel.imageIntent(), action)
        }

        radioButton.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnImageLabel -> action = IMAGE_LABEL
                R.id.btnTextRecognition -> action = TEXT_RECOGNITION
                R.id.btnSmartReply -> action = SMART_REPLY
            }
        }
    }

    private fun imageRecognitionAction(data: Intent) {
        try {
            txtResult.text = "loading..."
            val stream = contentResolver!!.openInputStream(data.getData())
            if (::photoImage.isInitialized) photoImage.recycle()
            photoImage = BitmapFactory.decodeStream(stream)
            firebaseImage = FirebaseVisionImage.fromBitmap(photoImage)
            imageResult.setImageBitmap(photoImage)
            when (action) {
                IMAGE_LABEL -> imageLabelAction()
                TEXT_RECOGNITION -> imageTextAction()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun imageLabelAction() {
        var text = ""
        labelViewModel.labelDeviceDetector.detectInImage(firebaseImage)
                .addOnSuccessListener {
                    if (it != null && it.isNotEmpty()) {
                        for (i in it.indices)
                            text += it[i].label + " = " + it[i].confidence + "\n"
                    }
                    txtResult.text = text
                }
//        labelViewModel.labelCloudDetector.processImage(firebaseImage)
//                .addOnSuccessListener {
//                    if (it != null && it.isNotEmpty()) txtResult.text = it[0].label
//                }
    }

    private fun imageTextAction() {
        var text = ""
        labelViewModel.textDeviceDetector.processImage(firebaseImage)
                .addOnSuccessListener {
                    for (block in it.textBlocks) text += block.text + "\n"
                    txtResult.text = text
                }

//        labelViewModel.textCloudDetector.processImage(firebaseImage)
//                .addOnSuccessListener {
//                    text = ""
//                    text += it.text+"\n"
//                    txtResult.text = text
//                }
    }

    private fun faceDetectionAction(data: Intent) {
        //todo == waiting to implement fun
        txtResult.text = "face Detection Action"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                IMAGE_LABEL, TEXT_RECOGNITION -> imageRecognitionAction(data!!)
                SMART_REPLY -> faceDetectionAction(data!!)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 0) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                imageResult.setEnabled(true)
            }
        }
    }
}
