package com.dsm.dsmmarketandroid.presentation.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.findPassword.FindPasswordActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tb_login.setNavigationOnClickListener { finish() }

        tv_forget_password.setOnClickListener { startActivity<FindPasswordActivity>() }
    }
}
