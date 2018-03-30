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
import kotlinx.android.synthetic.main.activity_image.*
import java.io.FileNotFoundException
import java.io.InputStream


class ImageActivity : AppCompatActivity() {

    private val code = 1001
    private lateinit var photoImage: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        checkPermission()
        btnCamera.setOnClickListener {
            takePicture()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            btnCamera.setEnabled(false)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    private fun takePicture() {
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
                if (photoImage != null) photoImage.recycle()
                stream = contentResolver!!.openInputStream(data.getData())
                photoImage = BitmapFactory.decodeStream(stream)
                imageResult.setImageBitmap(photoImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 0) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                btnCamera.setEnabled(true)
            }
        }
    }
}
