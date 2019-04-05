package edu.weather.wreport.ui.splash

import android.os.Bundle
import edu.weather.wreport.ui.base.BaseActivity
import edu.weather.wreport.ui.home.homeActivityIntent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Observable.timer(2, TimeUnit.SECONDS, Schedulers.io())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    startActivity(homeActivityIntent())
                    finish()
                })
    }
}

