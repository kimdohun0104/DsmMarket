package com.dsm.dsmmarketandroid.presentation.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.dsm.domain.usecase.ReportPostUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.util.onItemSelectedListener
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dialog_post_report.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject

class ReportPostDialog : DialogFragment() {

    private val reportPostUseCase: ReportPostUseCase by inject()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_post_report, container, false)

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
                til_reason.visibility = View.INVISIBLE
            }
        }

        btn_confirm.setOnClickListener {
            val reason = if (isEtc) {
                et_reason.text.toString().trim()
            } else {
                spn_reason.selectedItem.toString()
            }

            compositeDisposable.add(
                reportPostUseCase.create(
                    hashMapOf(
                        "postId" to arguments?.getInt("post_id"),
                        "type" to arguments?.getInt("type"),
                        "reason" to reason
                    )
                ).subscribe({
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