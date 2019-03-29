package edu.weather.wreport.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import edu.rush.myrush.ui.home.HomeFragment
import edu.weather.wreport.R
import edu.weather.wreport.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*
import timber.log.Timber

fun Context.homeActivityIntent(): Intent {
    return Intent(this, HomeActivity::class.java)
}

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setUpToolBar()
        setUpItemList()
    }

    private fun setUpItemList() {
        var homeFragment: HomeFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as? HomeFragment
        if (homeFragment == null) {
            // Create the fragment
            homeFragment = HomeFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.contentFrame, homeFragment, HomeFragment.TAG)
                    .commit()
        }
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolbar)
    }
}