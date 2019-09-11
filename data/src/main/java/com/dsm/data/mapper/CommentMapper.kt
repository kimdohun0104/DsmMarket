package com.dsm.data.mapper

import com.dsm.data.remote.entity.CommentEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Comment

class CommentMapper : Mapper<CommentEntity, Comment> {
    override fun mapFrom(from: CommentEntity) = Comment(
        nick = from.nick,
        content = from.content
    )
}