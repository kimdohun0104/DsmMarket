package com.dsm.dsmmarketandroid.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.data.local.pref.PrefHelper
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.chatList.ChatListFragment
import com.dsm.dsmmarketandroid.presentation.ui.me.MeFragment
import com.dsm.dsmmarketandroid.presentation.ui.post.PostDialog
import com.dsm.dsmmarketandroid.presentation.ui.purchase.PurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.rent.RentFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val backSubject = BehaviorSubject.createDefault(0L).toSerialized()

    private val backSubjectDisposable =
        backSubject.buffer(2, 1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it[1] - it[0] <= 1500) finish()
                else toast(getString(R.string.back_to_exit))
            }

    override fun onBackPressed() {
        backSubject.onNext(System.currentTimeMillis())
    }

    private val fragments = listOf(PurchaseFragment(), RentFragment(), ChatListFragment(), MeFragment())
    private var currentPosition = -1

    private val prefHelper: PrefHelper by inject()

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

    override fun onDestroy() {
        backSubjectDisposable.dispose()
        super.onDestroy()
    }
}

