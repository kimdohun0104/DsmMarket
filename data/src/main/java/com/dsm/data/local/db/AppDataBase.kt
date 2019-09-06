package com.dsm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.entity.PurchaseRoomEntity

@Database(entities = [PurchaseRoomEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun purchaseDao(): PurchaseDao
}