package com.dsm.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsm.data.local.db.entity.CommentRoomEntity
import io.reactivex.Completable

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addComment(comment: List<CommentRoomEntity>): Completable

    @Query("SELECT * from CommentRoomEntity WHERE postId = :postId AND type = :type")
    fun getCommentList(postId: Int, type: Int): List<CommentRoomEntity>?
}