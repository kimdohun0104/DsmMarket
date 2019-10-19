package com.dsm.dsmmarketandroid.presentation.ui.language

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.BaseApp
import com.dsm.dsmmarketandroid.presentation.ui.main.MainActivity
import com.dsm.dsmmarketandroid.presentation.util.LocaleManager.LANGUAGE_ENGLISH
import com.dsm.dsmmarketandroid.presentation.util.LocaleManager.LANGUAGE_KOREAN
import kotlinx.android.synthetic.main.dialog_change_language.*

class ChangeLanguageDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_change_language, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lang = arguments?.getString("lang")

        if (lang == LANGUAGE_ENGLISH) {
            tv_language.text = getString(R.string.english)
        } else if (lang == LANGUAGE_KOREAN) {
            tv_language.text = getString(R.string.korean)
        }

        btn_complete.setOnClickListener {
            BaseApp.localeManager?.setNewLocale(activity!!, lang)
            Intent(activity!!, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
        }

        btn_cancel.setOnClickListener { dismiss() }
    }
}