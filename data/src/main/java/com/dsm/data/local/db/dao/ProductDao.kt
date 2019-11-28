package com.dsm.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.db.entity.ProductRoomEntity
import io.reactivex.Completable

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProductList(productList: List<ProductRoomEntity>) : Completable

    @Query("SELECT * FROM ProductRoomEntity ORDER BY postId DESC LIMIT :pageSize OFFSET :page")
    fun getProductList(page: Int, pageSize: Int): List<ProductRoomEntity>
}