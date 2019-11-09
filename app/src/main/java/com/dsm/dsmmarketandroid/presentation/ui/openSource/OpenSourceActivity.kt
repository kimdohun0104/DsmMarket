package com.dsm.dsmmarketandroid.presentation.ui.openSource

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.activity_open_source.*
import org.jetbrains.anko.configuration

class OpenSourceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_source)
        tb_open_source.setNavigationOnClickListener { finish() }

        when (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> wv_open_source.loadUrl("file:///android_asset/open_source_licenses.html")
            Configuration.UI_MODE_NIGHT_YES -> wv_open_source.loadUrl("file:///android_asset/open_source_licenses_dark.html")
        }

    }
}
