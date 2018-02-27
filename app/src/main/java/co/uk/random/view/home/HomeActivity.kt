package co.uk.random.view.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import javax.inject.Inject

import co.uk.random.R

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
