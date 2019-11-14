package com.yogyakarta.yogyakartaapp.ui

import android.content.Intent
import android.os.Bundle
import com.yogyakarta.yogyakartaapp.R
import com.yogyakarta.yogyakartaapp.base.BaseActivity
import com.yogyakarta.yogyakartaapp.ui.movie.MovieActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_login_signIn.setOnClickListener {
            Intent(this, MovieActivity::class.java).run {
                startActivity(this)
                finish()
            }
        }

        textView_login_signUp.setOnClickListener {
            Intent(this,RegisterActivity::class.java).run {
                startActivity(this)
            }
        }
    }
}
