package co.uk.spltech.motion

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.uk.spltech.receipts.R
import kotlinx.android.synthetic.main.activity_motion_one.*


class MotionOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_one)
        setUI()
    }

    private fun setUI() {
        if (motionLayoutOne.progress > 0.5f) motionLayoutOne.transitionToStart()
        else motionLayoutOne.transitionToEnd()
    }
}
