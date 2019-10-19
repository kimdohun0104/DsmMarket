package com.dsm.dsmmarketandroid.presentation.ui.password.changePassword

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityChangePasswordBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_change_password.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_change_password

    private val viewModel: ChangePasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tb_change_password.setNavigationOnClickListener { finish() }
        val authCode = intent.getIntExtra("authCode", 0)
        val email = intent.getStringExtra("email") ?: ""
        binding.authCode = authCode
        binding.email = email

        val rotateAnim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
        view_ring.startAnimation(rotateAnim)

        et_new_password_confirm.setEditorActionListener(EditorInfo.IME_ACTION_DONE) { viewModel.changePassword(email, authCode) }

        viewModel.toastPasswordDiffEvent.observe(this, Observer { toast(getString(R.string.fail_diff_password)) })

        viewModel.toastServerErrorEvent.observe(this, Observer { toast(getString(R.string.fail_server_error)) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        binding.viewModel = viewModel
    }
}
