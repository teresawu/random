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
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_mlkit.*
import java.io.FileNotFoundException
import javax.inject.Inject

class MLKitActivity : DaggerAppCompatActivity() {
    private val CHOOSE_IMAGE = 1001
    private lateinit var photoImage: Bitmap
    @Inject
    lateinit var labelViewModel: LabelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mlkit)
        checkPermission()
        imageResult.setOnClickListener {
            choosePicture()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    private fun choosePicture() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, CHOOSE_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == CHOOSE_IMAGE && resultCode == Activity.RESULT_OK)
            try {
                val stream = contentResolver!!.openInputStream(data.getData())
                if (::photoImage.isInitialized) photoImage.recycle()
                photoImage = BitmapFactory.decodeStream(stream)
                imageResult.setImageBitmap(photoImage)
                labelViewModel.detectObject(photoImage).subscribeBy(
                        onSuccess = {
                            txtResult.text = it[0].text
                        }
                )
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
}
