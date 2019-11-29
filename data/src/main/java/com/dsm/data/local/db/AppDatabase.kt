package com.dsm.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dsm.data.local.db.dao.*
import com.dsm.data.local.db.entity.*

@Database(entities = [PurchaseDetailRoomEntity::class, RentDetailRoomEntity::class, SearchHistoryRoomEntity::class, ProductRoomEntity::class, InterestProductRoomEntity::class], version = 5)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun purchaseDao(): PurchaseDao

    abstract fun rentDao(): RentDao

    abstract fun searchDao(): SearchDao

    abstract fun productDao(): ProductDao

    abstract fun interestDao(): InterestDao
}