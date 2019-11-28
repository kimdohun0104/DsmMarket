package com.dsm.dsmmarketandroid.presentation.ui.main.me.interest

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivityInterestBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.InterestPagerAdapter
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import com.dsm.dsmmarketandroid.presentation.util.addOnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_interest.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class InterestActivity : BaseActivity<ActivityInterestBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_interest

    private val viewModel: InterestViewModel by viewModel()

    override fun viewInit() {
        tb_interest.setNavigationOnClickListener { finish() }

        vp_interest.adapter = InterestPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tl_interest, vp_interest, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.purchase)
                1 -> tab.text = getString(R.string.rent)
            }
        }.attach()

        var isRentLoaded = false
        tl_interest.addOnTabSelectedListener {
            vp_interest.currentItem = it.position
            if (it.position == 1 && !isRentLoaded) {
                viewModel.getInterest(ProductType.RENT)
                isRentLoaded = true
            }
        }
    }

    override fun observeViewModel() {
        viewModel.toastEvent.observe(this, Observer { toast(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
    }
}
