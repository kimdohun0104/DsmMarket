package com.dsm.data.mapper

import com.dsm.data.remote.entity.PostCategoryEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.PostCategory

class PostCategoryMapper : Mapper<List<PostCategoryEntity>, List<PostCategory>> {
    override fun mapFrom(from: List<PostCategoryEntity>): List<PostCategory> =
        from.map {
            PostCategory(
                parent = it.parent,
                child = it.body
            )
        }
}