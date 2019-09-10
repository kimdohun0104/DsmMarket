package com.dsm.dsmmarketandroid.presentation.ui.start

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginActivity
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_start.btn_login
import kotlinx.android.synthetic.main.activity_start.btn_sign_up

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val ivLogo = findViewById<View>(R.id.iv_logo)

        btn_login.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(this, ivLogo, "logo")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent, options.toBundle())
        }

        btn_sign_up.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(this, ivLogo, "logo")
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent, options.toBundle())
        }
    }
}
