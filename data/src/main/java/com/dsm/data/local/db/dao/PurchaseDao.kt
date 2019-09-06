package com.dsm.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.db.entity.PurchaseRoomEntity
import io.reactivex.Completable

@Dao
interface PurchaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun cachePurchase(purchaseRoomEntity: PurchaseRoomEntity): Completable

    @Query("SELECT * FROM purchaseroomentity LIMIT :page OFFSET :pageSize")
    fun getPurchaseList(page: Int, pageSize: Int): List<PurchaseRoomEntity>
}