package co.uk.random.view.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import co.uk.random.R
import co.uk.random.util.RealmHelper
import co.uk.random.util.extension.addFragment
import co.uk.random.util.extension.replaceFragment
import co.uk.random.view.channel.ChannelFragment
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.view_home.*
import javax.inject.Inject


class HomeActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val channelFragment = ChannelFragment.newInstance()
    private val homePageAdapter by lazy { HomePageAdapter(supportFragmentManager) }

    private val toggle by lazy {
        ActionBarDrawerToggle(
                this, drawerLayout, homeToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUI()
    }

    private fun setUI() {
        setSupportActionBar(homeToolbar)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        replaceFragment(channelFragment, R.id.homeFragmentLayout)
        homeViewPager.adapter = homePageAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        RealmHelper.clearAllCache()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.item_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) true
        else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
