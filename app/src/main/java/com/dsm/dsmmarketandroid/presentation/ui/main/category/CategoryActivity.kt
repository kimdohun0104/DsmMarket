package com.dsm.dsmmarketandroid.presentation.ui.main.category

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import kotlinx.android.synthetic.main.activity_category.*
import org.jetbrains.anko.startActivity

class CategoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        tb_category.setNavigationOnClickListener { finish() }
    }

    fun onClickCategory(v: View) {
        val parent = v as LinearLayout
        val category = (parent.getChildAt(1) as TextView).text.toString()
        startActivity<CategoryListActivity>("category" to category)

        Analytics.logEvent(this, Analytics.SELECT_CATEGORY, Bundle().apply { putString(Analytics.CATEGORY, category) })
    }
}
