package com.dsm.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.db.entity.SearchHistoryRoomEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSearchHistory(searchHistoryRoomEntity: SearchHistoryRoomEntity): Completable

    @Query("select * from SearchHistoryRoomEntity")
    fun getSearchHistory(): Flowable<List<SearchHistoryRoomEntity>>

    @Query("delete from SearchHistoryRoomEntity where content = :content")
    fun deleteSearchHistory(content: String): Completable
}