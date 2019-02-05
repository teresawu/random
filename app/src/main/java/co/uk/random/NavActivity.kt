package co.uk.random

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.co.uk.tensorflow.view.ImageActivity
import app.co.uk.tensorflow.view.VideoActivity
import co.uk.mlkit.MLKitActivity
import co.uk.spltech.receipts.ReceiptsActivity
import co.uk.youtube.view.home.HomeActivity
import io.reactivex.Single
import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject
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

        val publish = AsyncSubject.create<Int>()
        publish.subscribe { Log.i("NavActivity 1 = ", it.toString()) }
        publish.onNext(1)
        publish.onNext(2)
        publish.onNext(3)
        publish.subscribe { Log.i("NavActivity 2 = ", it.toString()) }
        publish.subscribe { Log.i("NavActivity 3 = ", it.toString()) }
    }
}
