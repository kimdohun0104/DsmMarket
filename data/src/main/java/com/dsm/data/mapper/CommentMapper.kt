package com.dsm.data.mapper

import com.dsm.data.remote.entity.CommentListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Comment

class CommentMapper : Mapper<CommentListEntity, List<Comment>> {
    override fun mapFrom(from: CommentListEntity): List<Comment> =
        from.commentLis.map {
            Comment(
                nick = it.nick,
                content = it.content,
                createdAt = it.createdAt
            )
        }
}