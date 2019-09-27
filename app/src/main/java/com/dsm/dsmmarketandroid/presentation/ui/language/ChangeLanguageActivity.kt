package com.dsm.dsmmarketandroid.presentation.ui.language

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.activity_change_language.*

class ChangeLanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)
        tb_change_language.setNavigationOnClickListener { finish() }

        val preferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        cl_korean.setOnClickListener { preferences.edit().putString("lang", "kr").apply() }

        cl_english.setOnClickListener { preferences.edit().putString("lang", "en").apply() }
    }
}
