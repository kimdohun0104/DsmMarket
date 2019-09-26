package com.dsm.dsmmarketandroid.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.chatList.ChatFragment
import com.dsm.dsmmarketandroid.presentation.ui.me.MeFragment
import com.dsm.dsmmarketandroid.presentation.ui.post.PostBottomFragment
import com.dsm.dsmmarketandroid.presentation.ui.purchase.PurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.rent.RentFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(tb_main)
        replaceView(PurchaseFragment())

        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.purchase -> replaceView(PurchaseFragment())
                R.id.rent -> replaceView(RentFragment())
                R.id.post -> {
                    PostBottomFragment().show(supportFragmentManager, "")
                    return@setOnNavigationItemSelectedListener false
                }
                R.id.chatting -> replaceView(ChatFragment())
                R.id.me -> replaceView(MeFragment())
            }
            true
        }
    }

    private fun replaceView(fragment: Fragment) =
        supportFragmentManager.beginTransaction().replace(R.id.ll_container, fragment).commit()

    override fun onDestroy() {
        backSubjectDisposable.dispose()
        super.onDestroy()
    }
}

