package com.dsm.dsmmarketandroid.presentation.ui.myPost.rent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.MyPostRentListAdapter
import kotlinx.android.synthetic.main.fragment_my_post_rent.view.*

class MyRentPostFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_my_post_rent, container, false)

        val adapter = MyPostRentListAdapter()
        rootView.rv_my_post_rent.adapter = adapter

        adapter.addItems(
            arrayListOf(
                ProductModel("http://img.allurekorea.com/allure/2018/12/style_5c248d8d46c15.jpg", "미칠듯이 좋은 화장품, 한정판매 간다.", "2019-03-01", "1000"),
                ProductModel("https://s-i.huffpost.com/gen/3874780/images/n-GETTYIMAGESBANK-628x314.jpg", "우리 할머니 화장품 팝니다. 돈이 급해서 싸게 팜@@", "2019-03-01", "20000")
            )
        )

        return rootView
    }
}