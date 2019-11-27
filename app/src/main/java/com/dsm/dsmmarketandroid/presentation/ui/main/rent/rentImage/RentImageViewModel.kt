package com.dsm.dsmmarketandroid.presentation.ui.main.rent.rentImage

import androidx.lifecycle.MutableLiveData
import com.dsm.domain.usecase.GetRentImageUseCase
import com.dsm.dsmmarketandroid.presentation.base.BaseViewModel

class RentImageViewModel(private val getRentImageUseCase: GetRentImageUseCase) : BaseViewModel() {

    val image = MutableLiveData<String>()

    fun getRentImage(postId: Int) {
        addDisposable(
            getRentImageUseCase.create(postId)
                .subscribe({
                    image.value = it
                }, {
                })
        )
    }
}