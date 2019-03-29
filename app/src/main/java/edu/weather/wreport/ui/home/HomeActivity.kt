package edu.weather.wreport.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolbar)
    }
}