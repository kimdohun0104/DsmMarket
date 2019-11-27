package com.dsm.dsmmarketandroid.presentation.ui.start

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.auth.login.LoginActivity
import com.dsm.dsmmarketandroid.presentation.ui.auth.signUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val ivStartLogo = findViewById<View>(R.id.iv_start_logo)

        btn_start_login.setOnClickListener {
            btn_start_login.isEnabled = false
            startActivity(
                Intent(this, LoginActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this, ivStartLogo, "logo").toBundle()
            )
        }

        btn_start_sign_up.setOnClickListener {
            btn_start_sign_up.isEnabled = false
            startActivity(
                Intent(this, SignUpActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this, ivStartLogo, "logo").toBundle()
            )
        }
    }

    override fun onResume() {
        btn_start_login.isEnabled = true
        btn_start_sign_up.isEnabled = true
        super.onResume()
    }
}
