package com.dsm.dsmmarketandroid.presentation.ui.start

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.login.LoginActivity
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val ivLogo = findViewById<View>(R.id.iv_logo)

        btn_login.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this, ivLogo, "logo").toBundle()
            )
        }

        btn_sign_up.setOnClickListener {
            startActivity(
                Intent(this, SignUpActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this, ivLogo, "logo").toBundle()
            )
        }
    }
}
