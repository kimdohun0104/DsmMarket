package com.dsm.dsmmarketandroid.presentation.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.adapter.RecentSearchListAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        ib_back.setOnClickListener { finish() }

        val adapter = RecentSearchListAdapter()
        rv_recent_search.adapter = adapter
        adapter.addItems(listOf("간장", "고추장", "춘장", "된장"))
    }
}
