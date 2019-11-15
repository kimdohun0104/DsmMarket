package com.dsm.dsmmarketandroid.presentation.ui.password.changePassword

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityChangePasswordBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import kotlinx.android.synthetic.main.activity_change_password.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordActivity : BaseActivity<ActivityChangePasswordBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_change_password

    private val viewModel: ChangePasswordViewModel by viewModel()

    override fun viewInit() {
        tb_change_password.setNavigationOnClickListener { finish() }

        val rotateAnim = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
        view_ring.startAnimation(rotateAnim)

        et_new_password_confirm.setEditorActionListener(EditorInfo.IME_ACTION_DONE) { if (btn_change_password.isClickable) viewModel.changePassword() }
    }

    override fun observeViewModel() {
        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        viewModel.toastEvent.observe(this, Observer { toast(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
