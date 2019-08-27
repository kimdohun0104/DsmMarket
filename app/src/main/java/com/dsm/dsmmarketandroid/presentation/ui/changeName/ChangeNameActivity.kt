package com.dsm.dsmmarketandroid.presentation.ui.changeName

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.activity_change_name.*

class ChangeNameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_name)
        tb_change_name.setNavigationOnClickListener { finish() }
    }
}
