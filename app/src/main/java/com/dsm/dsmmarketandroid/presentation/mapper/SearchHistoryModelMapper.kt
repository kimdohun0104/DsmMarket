package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.SearchHistory
import com.dsm.dsmmarketandroid.presentation.model.SearchHistoryModel

class SearchHistoryModelMapper : Mapper<List<SearchHistory>, List<SearchHistoryModel>> {
    override fun mapFrom(from: List<SearchHistory>): List<SearchHistoryModel> =
        from.map {
            SearchHistoryModel(
                content = it.content
            )
        }
}