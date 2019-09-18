package com.dsm.dsmmarketandroid.presentation.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import com.dsm.domain.usecase.ReportCommentUseCase
import com.dsm.dsmmarketandroid.R
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_comment_report.view.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class ReportCommentDialog : DialogFragment() {

    private val reportCommentUseCase: ReportCommentUseCase by inject()

    private val compositeDisposable = CompositeDisposable()

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

        rootView.btn_confirm.setOnClickListener {
            val reason = if (isEtc) {
                rootView.et_reason.text.toString().trim()
            } else {
                rootView.spn_reason.selectedItem.toString()
            }

            compositeDisposable.add(
                reportCommentUseCase.create(
                    hashMapOf(
                        "postId" to arguments?.getInt("post_id"),
                        "type" to arguments?.getInt("type"),
                        "nick" to arguments?.getString("nick"),
                        "reason" to reason
                    )
                ).subscribe({
                    dismiss()
                }, {
                    activity?.toast(getString(R.string.fail_server_error))
                })
            )
        }

        return rootView
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}