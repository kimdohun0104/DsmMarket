package com.dsm.dsmmarketandroid.presentation.ui.me

import android.os.Bundle
import android.view.View
import com.dsm.data.local.pref.PrefHelper
import com.dsm.dsmmarketandroid.BuildConfig
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentMeBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.changeNick.ChangeNickActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.language.ChangeLanguageActivity
import com.dsm.dsmmarketandroid.presentation.ui.logout.LogoutDialog
import com.dsm.dsmmarketandroid.presentation.ui.myPost.MyPostActivity
import com.dsm.dsmmarketandroid.presentation.ui.openSource.OpenSourceActivity
import com.dsm.dsmmarketandroid.presentation.ui.password.passwordConfirm.PasswordConfirmActivity
import com.dsm.dsmmarketandroid.presentation.ui.recent.RecentActivity
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import kotlinx.android.synthetic.main.fragment_me.*
import kr.sdusb.libs.messagebus.MessageBus
import kr.sdusb.libs.messagebus.Subscribe
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class MeFragment : BaseFragment<FragmentMeBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_me

    private val prefHelper: PrefHelper by inject()

    override fun viewInit() {
        tv_version.text = BuildConfig.VERSION_NAME

        cl_me_logout.setOnClickListener { LogoutDialog().show(childFragmentManager, "") }

        activity?.run {
            title = prefHelper.getUserNick() + getString(R.string.my_page)

            cl_me_interest.setOnClickListener { startActivity<InterestActivity>() }
            cl_me_my_post.setOnClickListener { startActivity<MyPostActivity>() }
            cl_me_product_history.setOnClickListener { startActivity<RecentActivity>() }
            cl_me_change_name.setOnClickListener { startActivity<ChangeNickActivity>("nick" to prefHelper.getUserNick()) }
            cl_me_change_password.setOnClickListener { startActivity<PasswordConfirmActivity>() }
            cl_me_open_source.setOnClickListener { startActivity<OpenSourceActivity>() }
            cl_me_change_language.setOnClickListener { startActivity<ChangeLanguageActivity>() }
        }
    }

    override fun observeViewModel() {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MessageBus.getInstance().register(this)
    }

    @Subscribe(events = [MessageEvents.NICK_CHANGED_EVENT])
    fun onNickChanged() {
        activity?.title = prefHelper.getUserNick() + getString(R.string.my_page)
    }

    override fun onDestroy() {
        MessageBus.getInstance().unregister(this)
        super.onDestroy()
    }
}