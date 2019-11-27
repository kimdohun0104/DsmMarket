package com.dsm.dsmmarketandroid.presentation.ui.search.searchResult

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.dsm.domain.usecase.AddSearchHistoryUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.databinding.ActivitySearchResultBinding
import com.dsm.dsmmarketandroid.presentation.base.BaseActivity
import com.dsm.dsmmarketandroid.presentation.ui.adapter.SearchPagerAdapter
import com.dsm.dsmmarketandroid.presentation.util.Analytics
import com.dsm.dsmmarketandroid.presentation.util.addOnTabSelectedListener
import com.dsm.dsmmarketandroid.presentation.util.setEditorActionListener
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search_result.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class SearchResultActivity : BaseActivity<ActivitySearchResultBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search_result

    private val search: String by lazy { intent.getStringExtra("search") }

    private val addSearchHistoryUseCase: AddSearchHistoryUseCase by inject()
    private val composite = CompositeDisposable()

    override fun viewInit() {
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

        Analytics.logEvent(this, Analytics.SEARCH, Bundle().apply { putString("search", search) })
    }

    override fun observeViewModel() {
    }

    private fun startNewSearchResult() {
        val searchText = et_search.text.toString().trim()
        if (searchText.isNotBlank()) {
            startActivity<SearchResultActivity>("search" to searchText)
            finish()

            composite.add(addSearchHistoryUseCase.create(searchText).subscribe())
        }
    }

    override fun onDestroy() {
        composite.clear()
        super.onDestroy()
    }
}
