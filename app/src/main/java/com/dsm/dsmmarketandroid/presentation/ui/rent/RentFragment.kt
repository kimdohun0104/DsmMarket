package com.dsm.dsmmarketandroid.presentation.ui.rent

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RentListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.category.CategoryActivity
import com.dsm.dsmmarketandroid.presentation.ui.interest.InterestActivity
import com.dsm.dsmmarketandroid.presentation.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_rent.view.*
import org.jetbrains.anko.startActivity

class RentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_rent, container, false)
        setHasOptionsMenu(true)
        activity?.setTitle(R.string.rent)

        val adapter = RentListAdapter()
        rootView.rv_rent.adapter = adapter
        adapter.addItems(
            arrayListOf(
                ProductModel("http://img.allurekorea.com/allure/2018/12/style_5c248d8d46c15.jpg", "미칠듯이 좋은 화장품, 한정판매 간다.", "2019-03-01", "1000"),
                ProductModel("https://s-i.huffpost.com/gen/3874780/images/n-GETTYIMAGESBANK-628x314.jpg", "우리 할머니 화장품 팝니다. 돈이 급해서 싸게 팜@@", "2019-03-01", "20000")
            )
        )

        return rootView
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