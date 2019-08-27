package com.dsm.dsmmarketandroid.presentation.ui.purchaseDetail

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dsm.dsmmarketandroid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_purchase_detail.*

class PurchaseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_detail)
        setSupportActionBar(tb_purchase_detail)
        tb_purchase_detail.background.alpha = 0
        tb_purchase_detail.setNavigationOnClickListener { finish() }
        tb_purchase_detail.overflowIcon = ContextCompat.getDrawable(this, R.drawable.ic_menu)

        Picasso.get().load("http://recipe1.ezmember.co.kr/cache/recipe/2016/11/05/c4d779d37826aca82a44df01b3d4131c1.jpg").into(iv_product)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail_toolbar, menu)
        return true
    }
}
