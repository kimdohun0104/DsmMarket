package com.dsm.domain.entity

data class PostCategory(
    val parent: String,

    val child: List<String>
)