package co.uk.spltech.receipts

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_receipts_home.*
import java.io.FileNotFoundException
import javax.inject.Inject


class ReceiptsActivity : DaggerAppCompatActivity() {
    private val IMAGE_ACTION = 2001
    private val PERMISSION_ACTION = 2002
    private val CAMERA_ACTION = 2003
    private lateinit var photoImage: Bitmap
    private lateinit var firebaseImage: FirebaseVisionImage
    @Inject
    lateinit var receiptsViewModel: ReceiptsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipts_home)
        //check permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_ACTION)
        }
        setUI()
    }

    private fun setUI() {
        txtUpload.setOnClickListener {
            startActivityForResult(receiptsViewModel.uploadIntent(), IMAGE_ACTION)
        }

        txtCamera.setOnClickListener {
            val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_ACTION)
        }
    }

    private fun imageRecognitionAction(data: Intent) {
        try {
            val stream = contentResolver!!.openInputStream(data.getData())
            if (::photoImage.isInitialized) photoImage.recycle()
            photoImage = BitmapFactory.decodeStream(stream)
            firebaseImage = FirebaseVisionImage.fromBitmap(photoImage)
            imageResult.setImageBitmap(photoImage)
            imageTextAction()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun imageTextAction() {
        var total = ""
        var location = ""
        receiptsViewModel.textDeviceDetector.detectInImage(firebaseImage)
                .addOnSuccessListener {
                    for (block in it.blocks) total += block.text + "\n"
                    txtTotal.text = total
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (resultCode == Activity.RESULT_OK) {
            if (data != null && data.extras != null) imageRecognitionAction(data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ACTION)
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) imageResult.setEnabled(true)
    }
}
