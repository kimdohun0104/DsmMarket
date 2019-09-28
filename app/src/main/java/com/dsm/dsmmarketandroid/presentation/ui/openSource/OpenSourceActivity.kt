package com.dsm.dsmmarketandroid.presentation.ui.openSource

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.activity_open_source.*

class OpenSourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_source)
        tb_open_source.setNavigationOnClickListener { finish() }

        wv_open_source.loadUrl("file:///android_asset/open_source_licenses.html")
    }
}
