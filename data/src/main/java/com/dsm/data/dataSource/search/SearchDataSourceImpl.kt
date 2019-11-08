package com.dsm.data.dataSource.search

import com.dsm.data.addSchedulers
import com.dsm.data.local.db.dao.SearchDao
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import com.dsm.data.remote.Api
import io.reactivex.Completable
import io.reactivex.Flowable

class SearchDataSourceImpl(private val searchDao: SearchDao) : SearchDataSource {

    override fun addSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable =
        searchDao.addSearchHistory(searchHistoryRoomEntity).addSchedulers()

    override fun getSearchHistory(): Flowable<List<SearchHistoryRoomEntity>> =
        searchDao.getSearchHistory().addSchedulers()

    override fun deleteSearchHistory(content: String): Completable =
        searchDao.deleteSearchHistory(content).addSchedulers()
}