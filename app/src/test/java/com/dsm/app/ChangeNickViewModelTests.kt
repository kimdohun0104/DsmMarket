package com.dsm.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.domain.usecase.SendPasswordCodeUseCase
import com.dsm.dsmmarketandroid.presentation.ui.changeNick.ChangeNickViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ChangeNickViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var changeNickUseCase: ChangeNickUseCase
    private lateinit var viewModel: ChangeNickViewModel

    @Before
    fun init() {
        changeNickUseCase = mock(ChangeNickUseCase::class.java)
        viewModel = ChangeNickViewModel(changeNickUseCase)
    }

    @Test
    fun `when there's no blank isChangeNickEnable == true`() {
        viewModel.nick.value = "NICK"

        assertTrue(viewModel.isChangeNickEnable.test().value())
    }

    @Test
    fun `when there's blank isChangeNickEnable == false`() {
        viewModel.nick.value = ""

        assertTrue(viewModel.isChangeNickEnable.test().value())
    }

    @Test
    fun `change nick success (200)`() {
        viewModel.nick.value = "NICK"

        `when`(changeNickUseCase.create(viewModel.nick.value!!)).thenReturn(Flowable.just(200))

        viewModel.changeNick()

        viewModel.finishActivityEvent.test().assertHasValue()
    }

    @Test
    fun `already exist name (403)`() {
        viewModel.nick.value = "NICK"

        `when`(changeNickUseCase.create(viewModel.nick.value!!)).thenReturn(Flowable.just(403))

        viewModel.changeNick()

        viewModel.toastExistentNickEvent.test().assertHasValue()
    }
}