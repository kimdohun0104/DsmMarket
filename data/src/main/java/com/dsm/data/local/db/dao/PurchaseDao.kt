package com.dsm.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity
import io.reactivex.Completable

@Dao
interface PurchaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPurchaseDetail(purchaseDetailRoomEntity: PurchaseDetailRoomEntity): Completable

    @Query("select * from PurchaseDetailRoomEntity where id = :id")
    fun getPurchaseDetail(id: Int): PurchaseDetailRoomEntity
}