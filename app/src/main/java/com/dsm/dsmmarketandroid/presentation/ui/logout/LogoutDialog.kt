package com.dsm.dsmmarketandroid.presentation.ui.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dsm.data.local.pref.PrefHelper
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.start.StartActivity
import kotlinx.android.synthetic.main.dialog_logout.view.*
import org.koin.android.ext.android.inject

class LogoutDialog : DialogFragment() {

    private val prefHelper: PrefHelper by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.dialog_logout, container, false)

        rootView.btn_confirm.setOnClickListener {
            prefHelper.run {
                deleteAccessToken()
                deleteRefreshToken()
                deleteUserNick()
            }
            Intent(activity!!, StartActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(this)
            }
        }

        rootView.btn_cancel.setOnClickListener { dismiss() }

        return rootView
    }
}