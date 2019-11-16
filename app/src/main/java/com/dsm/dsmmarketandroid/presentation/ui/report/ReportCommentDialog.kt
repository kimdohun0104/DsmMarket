package com.dsm.dsmmarketandroid.presentation.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dsm.domain.usecase.ReportCommentUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.onItemSelectedListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_comment_report.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject

class ReportCommentDialog : DialogFragment() {

    private val reportCommentUseCase: ReportCommentUseCase by inject()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_comment_report, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var isEtc = false

        btn_cancel.setOnClickListener { dismiss() }

        spn_reason.onItemSelectedListener { position ->
            if (position != 2) {
                isEtc = false
                til_reason.visibility = View.GONE
            } else {
                isEtc = true
                til_reason.visibility = View.VISIBLE
            }
        }

        btn_confirm.setOnClickListener {
            val reason = if (isEtc) {
                et_reason.text.toString().trim()
            } else {
                spn_reason.selectedItem.toString()
            }

            compositeDisposable.add(
                reportCommentUseCase.create(
                    hashMapOf(
                        "postId" to arguments?.getInt("post_id"),
                        "type" to arguments?.getInt("type"),
                        "nick" to arguments?.getString("nick"),
                        "reason" to reason
                    )
                )
                    .doOnNext {
                        Analytics.logEvent(activity!!, Analytics.REPORT_COMMENT, Bundle().apply {
                            putInt(Analytics.POST_ID, arguments?.getInt("post_id") ?: -1)
                            putString(Analytics.REASON, reason)
                        })
                    }
                    .subscribe({
                        dismiss()
                    }, {
                        toast(getString(R.string.fail_server_error))
                    })
            )
        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}