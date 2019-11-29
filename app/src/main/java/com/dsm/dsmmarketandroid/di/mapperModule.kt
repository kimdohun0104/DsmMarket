package com.dsm.dsmmarketandroid.di

import com.dsm.data.mapper.InterestProductMapper
import com.dsm.data.mapper.ProductMapper
import com.dsm.dsmmarketandroid.presentation.mapper.CommentModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.PostCategoryModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.ProductModelMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { PostCategoryModelMapper() }

    factory { CommentModelMapper() }

    factory { ProductMapper() }

    factory { ProductModelMapper() }

    factory { InterestProductMapper() }

}