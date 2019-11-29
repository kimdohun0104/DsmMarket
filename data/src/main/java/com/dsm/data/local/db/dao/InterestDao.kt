package com.dsm.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.db.entity.InterestProductRoomEntity
import io.reactivex.Completable

@Dao
interface InterestDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInterestPurchase(interestPurchase: List<InterestProductRoomEntity>): Completable

    @Query("select * from InterestProductRoomEntity where type = 0")
    fun getInterestPurchase(): List<InterestProductRoomEntity>

    @Query("select * from InterestProductRoomEntity where type = 1")
    fun getInterestRent(): List<InterestProductRoomEntity>
}