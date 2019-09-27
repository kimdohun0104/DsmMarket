package com.dsm.dsmmarketandroid.presentation.ui.language

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
        val arg = Bundle()

        cl_korean.setOnClickListener {
            arg.putString("lang", LANGUAGE_KOREAN)
            changeLanguageDialog.arguments = arg
            changeLanguageDialog.show(supportFragmentManager, "")
        }

        cl_english.setOnClickListener {
            arg.putString("lang", LANGUAGE_ENGLISH)
            changeLanguageDialog.arguments = arg
            changeLanguageDialog.show(supportFragmentManager, "")
        }
    }
}
