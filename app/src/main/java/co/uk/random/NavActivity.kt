package co.uk.random

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.co.uk.tensorflow.view.ImageActivity
import app.co.uk.tensorflow.view.VideoActivity
import co.uk.spltech.receipts.ReceiptsActivity
import io.reactivex.subjects.AsyncSubject
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        btnTensorFlowImage.setOnClickListener { startActivity(Intent(this@NavActivity, ImageActivity::class.java)) }
        btnTensorFlowVideo.setOnClickListener { startActivity(Intent(this@NavActivity, VideoActivity::class.java)) }
        btnReceipts.setOnClickListener { startActivity(Intent(this@NavActivity, ReceiptsActivity::class.java)) }

        val publish = AsyncSubject.create<Int>()
        publish.subscribe { Log.i("NavActivity 1 = ", it.toString()) }
        publish.onNext(1)
        publish.onNext(2)
        publish.onNext(3)
        publish.subscribe { Log.i("NavActivity 2 = ", it.toString()) }
        publish.subscribe { Log.i("NavActivity 3 = ", it.toString()) }
    }
}
