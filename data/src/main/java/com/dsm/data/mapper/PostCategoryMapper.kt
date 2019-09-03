package com.dsm.data.mapper

import com.dsm.data.remote.entity.PostCategoryEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.PostCategory

class PostCategoryMapper : Mapper<List<PostCategoryEntity>, List<PostCategory>> {
    override fun mapFrom(from: List<PostCategoryEntity>): List<PostCategory> {
        val list = arrayListOf<PostCategory>()
        from.forEach { list.add(mapFrom(it)) }
        return list
    }

    private fun mapFrom(from: PostCategoryEntity): PostCategory =
        PostCategory(from.parent, from.body)
}