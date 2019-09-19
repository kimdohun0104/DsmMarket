package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.GetUserNickUseCase
import com.dsm.dsmmarketandroid.presentation.ui.me.MeViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MeViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getUserNickUseCase: GetUserNickUseCase

    private lateinit var viewModel: MeViewModel

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        viewModel = MeViewModel(getUserNickUseCase)
    }

    @Test
    fun `getUserNick success (200)`() {
        `when`(getUserNickUseCase.create(Unit)).thenReturn(Flowable.just("usernick"))

        viewModel.getUserNick()

        viewModel.userNick.test().assertValue("usernick")
    }
}