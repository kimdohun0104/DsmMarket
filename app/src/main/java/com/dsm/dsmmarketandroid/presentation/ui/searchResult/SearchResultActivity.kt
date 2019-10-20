package com.dsm.dsmmarketandroid.presentation.ui.searchResult

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySearchResultBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.SearchPagerAdapter
import com.dsm.dsmmarketandroid.presentation.util.addOnTabSelectedListener
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_search_result.*
import org.jetbrains.anko.startActivity

class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_search_result

    private val search: String by lazy { intent.getStringExtra("search") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ib_back.setOnClickListener { finish() }
        et_search.hint = search

        et_search.setEditorActionListener(EditorInfo.IME_ACTION_SEARCH) { startNewSearchResult() }

        ib_search.setOnClickListener { startNewSearchResult() }

        vp_search.adapter = SearchPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tl_search, vp_search, true) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.purchase)
                1 -> tab.text = getString(R.string.rent)
            }
        }.attach()

        tl_search.addOnTabSelectedListener { vp_search.currentItem = it.position }
    }

    private fun startNewSearchResult() {
        val search = et_search.text.toString().trim()
        if (search.isNotBlank()) {
            startActivity<SearchResultActivity>("search" to search)
            finish()
        }
    }
}
