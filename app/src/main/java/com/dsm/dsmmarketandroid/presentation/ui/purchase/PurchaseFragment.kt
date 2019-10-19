package com.dsm.dsmmarketandroid.presentation.ui.purchase

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentPurchaseBinding
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.category.CategoryActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.purchaseList.PurchaseListFragment
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchActivity
import org.jetbrains.anko.startActivity

class PurchaseFragment : BaseFragment<FragmentPurchaseBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_purchase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        childFragmentManager.beginTransaction().replace(R.id.fl_purchase_container, PurchaseListFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.menu_main_toolbar, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> activity?.startActivity<SearchActivity>()
            R.id.category -> activity?.startActivity<CategoryActivity>()
            R.id.interest -> activity?.startActivity<InterestActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}