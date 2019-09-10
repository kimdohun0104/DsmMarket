package com.dsm.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.db.entity.RentDetailRoomEntity
import io.reactivex.Completable

@Dao
interface RentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRentDetail(rentDetailRoomEntity: RentDetailRoomEntity): Completable

    @Query("select * from RentDetailRoomEntity where id = :id")
    fun getRentDetail(id: Int): RentDetailRoomEntity
}