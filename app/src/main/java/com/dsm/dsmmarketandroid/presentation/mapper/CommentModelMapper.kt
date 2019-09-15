package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Comment
import com.dsm.dsmmarketandroid.presentation.model.CommentModel

class CommentModelMapper : Mapper<List<Comment>, List<CommentModel>> {
    override fun mapFrom(from: List<Comment>): List<CommentModel> =
        from.map {
            CommentModel(
                nick = it.nick,
                createdAt = it.createdAt,
                content = it.content
            )
        }
}