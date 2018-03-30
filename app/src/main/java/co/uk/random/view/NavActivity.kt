package co.uk.random.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.co.uk.tensorflow.view.TensorFlowActivity
import co.uk.random.R
import co.uk.random.view.home.HomeActivity
import kotlinx.android.synthetic.main.activity_nav.*

class NavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        btnTensorFlow.setOnClickListener { startActivity(Intent(this@NavActivity, TensorFlowActivity::class.java)) }
        btnYoutube.setOnClickListener { startActivity(Intent(this@NavActivity, HomeActivity::class.java)) }
    }
}
