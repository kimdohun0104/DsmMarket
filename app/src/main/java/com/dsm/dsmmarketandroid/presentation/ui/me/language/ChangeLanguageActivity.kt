package com.dsm.dsmmarketandroid.presentation.ui.me.language

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.util.LocaleManager.LANGUAGE_ENGLISH
import com.dsm.dsmmarketandroid.presentation.util.LocaleManager.LANGUAGE_KOREAN
import kotlinx.android.synthetic.main.activity_change_language.*

class ChangeLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)
        tb_change_language.setNavigationOnClickListener { finish() }

        val changeLanguageDialog = ChangeLanguageDialog()

        cl_korean.setOnClickListener {
            changeLanguageDialog.apply {
                arguments = Bundle().apply { putString("lang", LANGUAGE_KOREAN) }
                show(supportFragmentManager, "")
            }
        }

        cl_english.setOnClickListener {
            changeLanguageDialog.apply {
                arguments = Bundle().apply { putString("lang", LANGUAGE_ENGLISH) }
                show(supportFragmentManager, "")
            }
        }
    }
}
