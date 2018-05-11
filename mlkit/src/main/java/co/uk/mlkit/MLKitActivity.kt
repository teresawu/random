package co.uk.mlkit

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_mlkit.*
import java.io.FileNotFoundException
import javax.inject.Inject

class MLKitActivity : DaggerAppCompatActivity() {
    private val IMAGE_LABEL = 1001
    private val TEXT_RECOGNITION = 1002
    private val FACE_DETECTION = 1003
    private var action = 0
    private lateinit var photoImage: Bitmap
    @Inject
    lateinit var labelViewModel: LabelViewModel
    private val buttons by lazy { arrayOf(btnImageLabel, btnFaceDetection, btnTextRecognition) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlkit)
        checkPermission()
        setUI()
    }

    private fun setUI() {
        imageResult.setOnClickListener {
            choosePicture(action)
        }

        radioButton.setOnCheckedChangeListener { radioGroup, checkedId ->
            if (checkedId == R.id.btnImageLabel) action = IMAGE_LABEL
            else if (checkedId == R.id.btnTextRecognition) action = TEXT_RECOGNITION
            else if (checkedId == R.id.btnFaceDetection) action = FACE_DETECTION
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    private fun choosePicture(action: Int) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, action)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                IMAGE_LABEL -> imageLabelAction(data)
                TEXT_RECOGNITION -> textRecognitionAction(data)
                FACE_DETECTION -> faceDetectionAction(data)
            }
        }
    }

    private fun imageLabelAction(data: Intent) {
        try {
            val stream = contentResolver!!.openInputStream(data.getData())
            if (::photoImage.isInitialized) photoImage.recycle()
            photoImage = BitmapFactory.decodeStream(stream)
            imageResult.setImageBitmap(photoImage)
            labelViewModel.detectObject(photoImage).subscribeBy(
                    onSuccess = {
                        //                            txtResult.text = it[0].text
                        txtResult.text = "image Label Action"
                    },
                    onError = {
                        Log.i("image label", it.message + "")
                    }
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun textRecognitionAction(data: Intent) {
        //todo == waiting to implement fun
        txtResult.text = "text Recognition Action"
    }

    private fun faceDetectionAction(data: Intent) {
        //todo == waiting to implement fun
        txtResult.text = "face Detection Action"
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
