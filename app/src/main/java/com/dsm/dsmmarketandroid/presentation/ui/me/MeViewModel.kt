package com.dsm.dsmmarketandroid.presentation.ui.me

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetUserNickUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel

class MeViewModel(private val getUserNickUseCase: GetUserNickUseCase) : BaseViewModel() {

    val userNick = MutableLiveData<String>()

    fun getUserNick() {
        addDisposable(
            getUserNickUseCase.create(Unit)
                .subscribe({
                    userNick.value = it
                }, {
                    userNick.value = ""
                })
        )
    }
}