package com.dsm.data.mapper

import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.SearchHistory

class SearchHistoryMapper : Mapper<SearchHistory, SearchHistoryRoomEntity> {
    override fun mapFrom(from: SearchHistory) = SearchHistoryRoomEntity(
        content = from.content
    )

    fun mapFrom(from: List<SearchHistoryRoomEntity>) : List<SearchHistory> =
        from.map {
            SearchHistory(
                content = it.content
            )
        }

}