package app.co.uk.tensorflow.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.co.uk.tensorflow.R
import app.co.uk.tensorflow.util.addFragment
import kotlinx.android.synthetic.main.activity_tensorflow.*


class TensorFlowActivity : AppCompatActivity() {
    private val tensorPageAdapter by lazy { TensorPageAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tensorflow)
        setUI()
    }

    private fun setUI() {
        addFragment(ImageFragment.newInstance(), R.id.tensorFragmentLayout)
        tensorViewPager.adapter = tensorPageAdapter
    }

    fun navigateToFragment(position: Int) {
        tensorViewPager.setCurrentItem(position, true)
    }

    override fun onBackPressed() {
        if (tensorViewPager.currentItem == 1) navigateToFragment(0)
        else super.onBackPressed()
    }
}
