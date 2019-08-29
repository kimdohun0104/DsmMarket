package com.dsm.dsmmarketandroid.presentation.ui.findPassword

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.changePassword.ChangePasswordActivity
import kotlinx.android.synthetic.main.activity_find_password.*
import org.jetbrains.anko.startActivity

class FindPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_password)
        tb_login.setNavigationOnClickListener { finish() }

        btn_find_password.setOnClickListener { startActivity<ChangePasswordActivity>() }
    }
}
