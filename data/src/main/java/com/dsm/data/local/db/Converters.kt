package com.dsm.data.local.db

import androidx.room.TypeConverter
import com.dsm.data.local.db.entity.InterestProductRoomEntity
import com.dsm.data.local.db.entity.ProductRoomEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object Converters {

    @TypeConverter
    @JvmStatic
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @JvmStatic
    @TypeConverter
    fun fromList(list: List<String>): String = Gson().toJson(list)

    @JvmStatic
    @TypeConverter
    fun fromProductListToString(list: List<ProductRoomEntity>) : String = Gson().toJson(list)

    @JvmStatic
    @TypeConverter
    fun fromStringToProductList(value: String) : List<ProductRoomEntity> {
        val listType = object : TypeToken<List<ProductRoomEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @JvmStatic
    @TypeConverter
    fun fromInterestListToString(list: List<InterestProductRoomEntity>) : String = Gson().toJson(list)

    @JvmStatic
    @TypeConverter
    fun fromStringToInterestList(value: String): List<InterestProductRoomEntity> {
        val listType = object : TypeToken<List<InterestProductRoomEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }
}