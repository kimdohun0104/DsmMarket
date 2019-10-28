package com.dsm.dsmmarketandroid.presentation.ui.rent

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.FragmentRentBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseFragment
import com.dsm.dsmmarketandroid.presentation.ui.category.CategoryActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.rentList.RentListFragment
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchActivity
import org.jetbrains.anko.startActivity


class RentFragment : BaseFragment<FragmentRentBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_rent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        childFragmentManager.beginTransaction().replace(R.id.fl_rent_container, RentListFragment()).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> activity?.startActivity<SearchActivity>()
            R.id.category -> activity?.startActivity<CategoryActivity>()
            R.id.interest -> activity?.startActivity<InterestActivity>()
        }
        return super.onOptionsItemSelected(item)
    }
}