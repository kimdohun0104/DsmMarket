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
import kotlinx.android.synthetic.main.dialog_change_language.view.*

class ChangeLanguageDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.dialog_change_language, container, false)

        val lang = arguments?.getString("lang")

        if (lang == LANGUAGE_ENGLISH) {
            rootView.tv_language.text = getString(R.string.english)
        } else if (lang == LANGUAGE_KOREAN) {
            rootView.tv_language.text = getString(R.string.korean)
        }

        rootView.btn_complete.setOnClickListener {
            BaseApp.localeManager?.setNewLocale(activity!!, lang)
            val intent = Intent(activity!!, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        rootView.btn_cancel.setOnClickListener { dismiss() }

        return rootView
    }
}