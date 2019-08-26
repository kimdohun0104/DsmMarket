package com.dsm.dsmmarketandroid.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.chat.ChatFragment
import com.dsm.dsmmarketandroid.presentation.ui.me.MeFragment
import com.dsm.dsmmarketandroid.presentation.ui.post.PostBottomFragment
import com.dsm.dsmmarketandroid.presentation.ui.purchase.PurchaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.rent.RentFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceView(PurchaseFragment())


        bnv_main.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.purchase -> replaceView(PurchaseFragment())
                R.id.rent -> replaceView(RentFragment())
                R.id.post -> PostBottomFragment().show(supportFragmentManager, "")
                R.id.chatting -> replaceView(ChatFragment())
                R.id.me -> replaceView(MeFragment())
            }
            true
        }
    }

    private fun replaceView(fragment: Fragment) =
        supportFragmentManager.beginTransaction().replace(R.id.ll_container, fragment).commit()
}
