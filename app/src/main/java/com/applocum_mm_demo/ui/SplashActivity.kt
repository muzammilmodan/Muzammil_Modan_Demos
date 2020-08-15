package com.applocum_mm_demo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.multidex.MultiDex
import com.applocum_mm_demo.R
import com.applocum_mm_demo.ui.login.LoginActivity
import com.applocum_mm_demo.ui.main.HomeActivity
import com.applocum_mm_demo.utils.GlobalValues
import com.applocum_mm_demo.utils.SessionManager.SessionManager
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mContext = SplashActivity@ this

        splashTimeOut()
    }

    private fun splashTimeOut() {
        try {
            val timer = Timer()
            timer.schedule(object : TimerTask() {

                override fun run() {


                    if (SessionManager.getIsUserLoggedin(mContext)) {
                        //Todo: With login
                        var intent = Intent(mContext, HomeActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }else{
                        //Todo: without login
                        var intent = Intent(mContext, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }

                }
            }, GlobalValues.SPLASH_TIMEOUT)
        } catch (e: Exception) {
            e.printStackTrace()

        }

    }
}
