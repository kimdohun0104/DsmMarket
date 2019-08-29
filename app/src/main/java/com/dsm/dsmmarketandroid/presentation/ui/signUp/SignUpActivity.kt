package com.dsm.dsmmarketandroid.presentation.ui.signUp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.SignUpPagerAdapter
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        tb_sign_up.setNavigationOnClickListener { finish() }

        vp_sign_up.adapter = SignUpPagerAdapter(supportFragmentManager)
        ci_sign_up.setupWithViewPager(vp_sign_up)
    }
}
