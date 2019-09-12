package com.dsm.dsmmarketandroid.presentation.ui.past

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.trash_model.ProductModel
import com.dsm.dsmmarketandroid.presentation.ui.adapter.PastListAdapter
import kotlinx.android.synthetic.main.activity_past.*

class PastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past)
        tb_past.setNavigationOnClickListener { finish() }

        val adapter = PastListAdapter()
        rv_past.adapter = adapter

        adapter.addItems(
            arrayListOf(
                ProductModel("http://img.allurekorea.com/allure/2018/12/style_5c248d8d46c15.jpg", "미칠듯이 좋은 화장품, 한정판매 간다.", "2019-03-01", "1000"),
                ProductModel("https://s-i.huffpost.com/gen/3874780/images/n-GETTYIMAGESBANK-628x314.jpg", "우리 할머니 화장품 팝니다. 돈이 급해서 싸게 팜@@", "2019-03-01", "20000")
            )
        )
    }
}
