package com.dsm.data.mapper

import com.dsm.data.local.db.entity.RecentRoomEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Recent

class RecentMapper : Mapper<List<RecentRoomEntity>, List<Recent>> {
    override fun mapFrom(from: List<RecentRoomEntity>): List<Recent> {
        val list = arrayListOf<Recent>()
        from.forEach {
            list.add(Recent(
                id = it.id,
                createdAt = it.createdAt,
                price = it.price,
                title = it.title
            ))
        }
        return list
    }
}