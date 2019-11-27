package com.dsm.dsmmarketandroid.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.data.local.pref.PrefHelper
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.chat.chatList.ChatListFragment
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.PurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.RentFragment
import com.dsm.dsmmarketandroid.presentation.ui.me.MeFragment
import com.dsm.dsmmarketandroid.presentation.ui.post.PostDialog
import com.dsm.dsmmarketandroid.presentation.util.MessageEvents
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import kr.sdusb.libs.messagebus.MessageBus
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), InstallStateUpdatedListener {

    companion object {
        private const val IN_APP_UPDATE_REQUEST = 1000
    }

    private val backSubject = BehaviorSubject.createDefault(0L).toSerialized()

    private val backSubjectDisposable =
        backSubject.buffer(2, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it[1] - it[0] <= 1500) finish()
                else toast(getString(R.string.back_to_exit))
            }

    override fun onBackPressed() =
        backSubject.onNext(System.currentTimeMillis())

    private val fragments = listOf(PurchaseFragment(), RentFragment(), ChatListFragment(), MeFragment())
    private var currentPosition = -1

    private val prefHelper: PrefHelper by inject()

    private val appUpdateManager: AppUpdateManager by lazy { AppUpdateManagerFactory.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tb_main)
        switchFragment(0)

        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.purchase -> switchFragment(0)
                R.id.rent -> switchFragment(1)
                R.id.post -> {
                    PostDialog().show(supportFragmentManager, "")
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.chatting -> switchFragment(2)
                R.id.me -> switchFragment(3)
            }
            true
        }

        bnv_main.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.purchase -> MessageBus.getInstance().handle(MessageEvents.SCROLL_TO_TOP_PURCHASE, null)
                R.id.rent -> MessageBus.getInstance().handle(MessageEvents.SCROLL_TO_TOP_RENT, null)
                R.id.chatting -> MessageBus.getInstance().handle(MessageEvents.SCROLL_TO_TOP_CHAT, null)
            }
        }

        inAppUpdate()
    }

    private fun switchFragment(index: Int) {
        if (index == currentPosition) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentByTag(index.toString()) == null) {
            transaction.add(R.id.ll_container, fragments[index], index.toString())
        }

        if (currentPosition != -1) {
            transaction.hide(fragments[currentPosition])
        }
        transaction.show(fragments[index])
        transaction.commit()

        title = when (index) {
            0 -> getString(R.string.purchase)
            1 -> getString(R.string.rent)
            2 -> getString(R.string.chatting)
            3 -> prefHelper.getUserNick() + getString(R.string.my_page)
            else -> getString(R.string.purchase)
        }

        currentPosition = index
    }

    private fun inAppUpdate() {
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
            ) {
                appUpdateManager.registerListener(this)
                appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, this, IN_APP_UPDATE_REQUEST)
            }
        }
    }

    override fun onStateUpdate(state: InstallState?) {
        if (state?.installStatus() == InstallStatus.DOWNLOADED) {
            Snackbar.make(cl_main_container, R.string.restart_to_update, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.restart) {
                    appUpdateManager.completeUpdate()
                    appUpdateManager.unregisterListener(this)
                }.show()
        }
    }

    override fun onDestroy() {
        backSubjectDisposable.dispose()
        appUpdateManager.unregisterListener(this)
        super.onDestroy()
    }
}

