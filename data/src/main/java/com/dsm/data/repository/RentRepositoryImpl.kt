package com.dsm.data.repository

import com.dsm.data.dataSource.rent.RentDataSource
import com.dsm.data.mapper.ProductMapper
import com.dsm.data.mapper.RentDetailMapper
import com.dsm.domain.entity.Product
import com.dsm.domain.entity.RentDetail
import com.dsm.domain.repository.RentRepository
import io.reactivex.Flowable
import retrofit2.HttpException

class RentRepositoryImpl(private val rentDataSource: RentDataSource) : RentRepository {

    private val productMapper = ProductMapper()
    private val rentDetailMapper = RentDetailMapper()

    override fun getRentList(page: Int, pageSize: Int, search: String, category: String): Flowable<List<Product>> =
        rentDataSource.getRentList(page, pageSize, search, category).map(productMapper::mapFrom)

    override fun getRentDetail(postId: Int): Flowable<RentDetail> =
        rentDataSource.getRemoteRentDetail(postId).map {
            if (it.code() == 200) rentDetailMapper.mapFrom(it.body()!!)
            else throw HttpException(it)
        }
            .doOnNext { rentDataSource.addLocalRentDetail(rentDetailMapper.mapFrom(it)).subscribe() }
            .onErrorReturn { rentDetailMapper.mapFrom(rentDataSource.getLocalRentDetail(postId)) }

    override fun modifyRent(params: Any): Flowable<Unit> =
        rentDataSource.modifyRent(params).map { if (it.code() != 200) throw HttpException(it) }
}