package app.co.uk.tensorflow.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import app.co.uk.tensorflow.R
import app.co.uk.tensorflow.util.Keys.INPUT_SIZE
import app.co.uk.tensorflow.util.Keys.LABEL_PATH
import app.co.uk.tensorflow.util.Keys.MODEL_PATH
import app.co.uk.tensorflow.util.ImageClassifier
import kotlinx.android.synthetic.main.activity_image.*
import java.io.FileNotFoundException
import java.io.InputStream


class ImageActivity : AppCompatActivity() {

    private val code = 1001
    private lateinit var photoImage: Bitmap
    private lateinit var classifier: ImageClassifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        classifier = ImageClassifier.create(getAssets(), MODEL_PATH, LABEL_PATH, INPUT_SIZE)
        checkPermission()
        imageResult.setOnClickListener {
            choosePicture()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            imageResult.setEnabled(false)
        }
    }

    private fun choosePicture() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, code)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        var stream: InputStream
        if (requestCode == code && resultCode == Activity.RESULT_OK)
            try {
                stream = contentResolver!!.openInputStream(data.getData())
                if (::photoImage.isInitialized) photoImage.recycle()
                photoImage = BitmapFactory.decodeStream(stream)
                photoImage = Bitmap.createScaledBitmap(photoImage, INPUT_SIZE, INPUT_SIZE, false)
                imageResult.setImageBitmap(photoImage)
                val results = classifier.recognizeImage(photoImage)
                txtResult.text = results.toString()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
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

    override fun onDestroy() {
        super.onDestroy()
        classifier.close()
    }
}
