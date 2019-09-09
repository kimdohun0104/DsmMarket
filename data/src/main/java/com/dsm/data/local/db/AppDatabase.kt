package com.dsm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dsm.data.local.db.dao.PurchaseDao
import com.dsm.data.local.db.entity.PurchaseDetailRoomEntity

@Database(entities = [PurchaseDetailRoomEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun purchaseDao(): PurchaseDao
}