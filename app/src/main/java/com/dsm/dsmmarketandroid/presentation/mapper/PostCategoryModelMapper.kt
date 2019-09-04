package com.dsm.dsmmarketandroid.presentation.mapper

import com.dsm.domain.base.Mapper
import com.dsm.domain.entity.PostCategory
import com.dsm.dsmmarketandroid.presentation.model.PostCategoryModel

class PostCategoryModelMapper : Mapper<List<PostCategory>, List<PostCategoryModel>> {
    override fun mapFrom(from: List<PostCategory>): List<PostCategoryModel> {
        val list = arrayListOf<PostCategoryModel>()
        from.forEach {
            list.add(PostCategoryModel.ParentCategory(it.parent, null))
            it.child.forEach { child ->
                list.add(PostCategoryModel.ChildCategory(child, it.parent))
            }
        }
        return list
    }

}