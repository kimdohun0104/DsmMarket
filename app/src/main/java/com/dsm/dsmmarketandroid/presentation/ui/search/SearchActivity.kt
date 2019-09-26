package com.dsm.dsmmarketandroid.presentation.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySearchBinding
import com.dsm.dsmmarketandroid.presentation.ui.adapter.SearchHistoryListAdapter
import com.dsm.dsmmarketandroid.presentation.ui.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.searchResult.SearchResultActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_search

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ib_back.setOnClickListener { finish() }

        val adapter = SearchHistoryListAdapter(viewModel)
        rv_recent_search.adapter = adapter

        et_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search()
                true
            } else false
        }

        viewModel.getSearchHistory()

        viewModel.searchHistoryList.observe(this, Observer { adapter.setItems(it) })

        viewModel.intentSearchResult.observe(this, Observer { startActivity<SearchResultActivity>("search" to it) })

        viewModel.finishActivityEvent.observe(this, Observer { finish() })

        binding.viewModel = viewModel
    }
}
