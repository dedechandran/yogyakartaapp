package com.yogyakarta.yogyakartaapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.yogyakarta.yogyakartaapp.R
import com.yogyakarta.yogyakartaapp.base.BaseActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}
