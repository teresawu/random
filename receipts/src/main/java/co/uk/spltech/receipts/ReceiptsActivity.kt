package co.uk.spltech.receipts

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_receipts_home.*
import java.io.FileNotFoundException
import javax.inject.Inject


class ReceiptsActivity : DaggerAppCompatActivity() {
    private val UPLOAD_ACTION = 2001
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
            startActivityForResult(receiptsViewModel.uploadIntent(), UPLOAD_ACTION)
        }

        txtCamera.setOnClickListener {
            startActivityForResult(receiptsViewModel.cameraIntent(this), CAMERA_ACTION)
        }
    }

    private fun uploadAction(data: Intent) {
        try {
            val stream = contentResolver!!.openInputStream(data.getData())
            if (::photoImage.isInitialized) photoImage.recycle()
            photoImage = BitmapFactory.decodeStream(stream)
            firebaseImage = FirebaseVisionImage.fromBitmap(photoImage)
            imageResult.setImageBitmap(photoImage)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun cameraAction() {
        try {
            Picasso.with(this).load(receiptsViewModel.imageURI).into(imageResult)
            firebaseImage = FirebaseVisionImage.fromFilePath(this, receiptsViewModel.imageURI)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun textRecognitionAction() {
        var text = ""
        receiptsViewModel.textDeviceDetector.processImage(firebaseImage)
                .addOnSuccessListener {
                    for (block in it.textBlocks) text += block.text + "\n"
                    val receipts = receiptsViewModel.getReceipts(text)
                    editTotal.setText(receipts.total, TextView.BufferType.EDITABLE)
                    editLocation.setText(receipts.type, TextView.BufferType.EDITABLE)
                    editVAT.setText(receipts.vat, TextView.BufferType.EDITABLE)
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                UPLOAD_ACTION -> uploadAction(data!!)
                CAMERA_ACTION -> cameraAction()
            }
            textRecognitionAction()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ACTION)
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) imageResult.setEnabled(true)
    }
}
