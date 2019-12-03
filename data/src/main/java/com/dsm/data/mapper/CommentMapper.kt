package com.dsm.data.mapper

import com.dsm.data.local.db.entity.CommentRoomEntity
import com.dsm.data.remote.entity.CommentListEntity
import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.Comment

class CommentMapper : Mapper<CommentListEntity, List<Comment>> {
    override fun mapFrom(from: CommentListEntity): List<Comment> =
        from.commentLis.map {
            Comment(
                nick = it.nick,
                content = it.content,
                createdAt = it.createdAt,
                isMe = it.isMe
            )
        }

    fun mapFrom(from: CommentRoomEntity) = Comment(
        nick = from.nick,
        createdAt = from.createdAt,
        content = from.content,
        isMe = false
    )

    fun mapFrom(from: List<Comment>, postId: Int, type: Int): List<CommentRoomEntity> =
        from.map {
            CommentRoomEntity(
                nick = it.nick,
                content = it.content,
                createdAt = it.createdAt,
                type = type,
                postId = postId
            )
        }
}