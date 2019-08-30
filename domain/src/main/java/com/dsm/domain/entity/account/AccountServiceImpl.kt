package com.dsm.domain.entity.account

import com.dsm.domain.repository.AccountRepository
import io.reactivex.Flowable
import retrofit2.Response

class AccountServiceImpl(private val accountRepository: AccountRepository) : AccountService {
    override fun login(body: Any): Flowable<Response<Map<String, Any>>> =
        accountRepository.login(body).map {
            when (it.code()) {
                200 -> {
                    val response = it.body()!!
                    accountRepository.setAccessToken(response["access_token"] as String)
                    accountRepository.setRefreshToken(response["refresh_token"] as String)
                }
            }
            it
        }
}