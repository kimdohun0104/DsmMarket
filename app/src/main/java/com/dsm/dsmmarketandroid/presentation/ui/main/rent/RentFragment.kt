package com.dsm.dsmmarketandroid.presentation.ui.main.rent

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.category.CategoryActivity
import com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentList.RentListFragment
import com.dsm.dsmmarketandroid.presentation.ui.me.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchActivity
import org.jetbrains.anko.startActivity

class RentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_rent, container, false)

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