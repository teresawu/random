package co.uk.random

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.co.uk.tensorflow.view.ImageActivity
import app.co.uk.tensorflow.view.VideoActivity
import co.uk.mlkit.MLKitActivity
import co.uk.spltech.receipts.ReceiptsActivity
import co.uk.youtube.view.home.HomeActivity
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        btnMLKit.setOnClickListener { startActivity(Intent(this@NavActivity, MLKitActivity::class.java)) }
        btnTensorFlowImage.setOnClickListener { startActivity(Intent(this@NavActivity, ImageActivity::class.java)) }
        btnTensorFlowVideo.setOnClickListener { startActivity(Intent(this@NavActivity, VideoActivity::class.java)) }
        btnYoutube.setOnClickListener { startActivity(Intent(this@NavActivity, HomeActivity::class.java)) }
        btnReceipts.setOnClickListener { startActivity(Intent(this@NavActivity, ReceiptsActivity::class.java)) }
    }
}
