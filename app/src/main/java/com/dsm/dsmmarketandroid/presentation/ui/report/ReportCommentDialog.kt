package com.dsm.dsmmarketandroid.presentation.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import com.dsm.dsmmarketandroid.R
import kotlinx.android.synthetic.main.dialog_comment_report.view.*

class ReportCommentDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.dialog_comment_report, container, false)

        var isEtc = false

        rootView.btn_cancel.setOnClickListener { dismiss() }

        rootView.spn_reason.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != 2) {
                    isEtc = false
                    rootView.til_reason.visibility = View.GONE
                } else {
                    isEtc = true
                    rootView.til_reason.visibility = View.VISIBLE
                }
            }

        }

        return rootView
    }
}