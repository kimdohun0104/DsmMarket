package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.SendPasswordCodeUseCase
import com.dsm.dsmmarketandroid.presentation.ui.password.sendPasswordCode.SendPasswordCodeViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SendPasswordCodeViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var sendPasswordCodeUseCase: SendPasswordCodeUseCase

    private lateinit var viewModel: SendPasswordCodeViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = SendPasswordCodeViewModel(sendPasswordCodeUseCase)
    }

    @Test
    fun `when there's no blank isSendPasswordCodeEnable == true`() {
        viewModel.email.value = "example@test.com"

        assertTrue(viewModel.isSendPasswordCodeEnable.test().value())
    }

    @Test
    fun `when there's blank isSendPasswordCodeEnable == false`() {
        viewModel.email.value = ""

        assertFalse(viewModel.isSendPasswordCodeEnable.test().value())
    }

    @Test
    fun `email invalid`() {
        viewModel.email.value = "example@testcom"

        viewModel.sendPasswordCode()

        viewModel.toastInvalidEmailEvent.test().assertHasValue()
    }

    @Test
    fun `send password code success (200)`() {
        viewModel.email.value = "example@test.com"

        `when`(sendPasswordCodeUseCase.create(viewModel.email.value!!))
            .thenReturn(Flowable.just(Unit))

        viewModel.sendPasswordCode()

        viewModel.intentPasswordCodeConfirmWithEmail.test().assertValue(viewModel.email.value)
    }
}