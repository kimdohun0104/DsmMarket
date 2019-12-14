package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.data.error.exception.ForbiddenException
import com.dsm.data.error.exception.InternalException
import com.dsm.data.error.exception.UnauthorizedException
import com.dsm.domain.usecase.ChangeNickUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.main.me.changeNick.ChangeNickViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class ChangeNickViewModelTests : BaseTest() {

    @Mock
    private lateinit var changeNickUseCase: ChangeNickUseCase

    private lateinit var viewModel: ChangeNickViewModel

    @Before
    fun init() {
        viewModel = ChangeNickViewModel(changeNickUseCase)
    }

    @Test
    fun `change nick button enable test`() {
        viewModel.nick.value = "NICK"

        assertTrue(viewModel.isChangeNickEnable.test().value())
    }

    @Test
    fun `change nick button disable test`() {
        viewModel.nick.value = ""

        assertFalse(viewModel.isChangeNickEnable.test().value())
    }

    @Test
    fun `change nick success test`() {
        viewModel.nick.value = "NICK"

        `when`(changeNickUseCase.create(viewModel.nick.value!!))
            .thenReturn(Flowable.just((Unit)))

        viewModel.changeNick()

        viewModel.finishActivityEvent.test().assertHasValue()
        viewModel.toastEvent.test().assertValue(R.string.success_change_nick)
    }

    @Test
    fun `change nick failed because already exist nick`() {
        viewModel.nick.value = "NICK"

        `when`(changeNickUseCase.create(viewModel.nick.value!!))
            .thenReturn(Flowable.error(ForbiddenException(createHttpException(403))))

        viewModel.changeNick()

        viewModel.toastEvent.test().assertValue(R.string.fail_existent_nick)
    }

    @Test
    fun `change nick unauthorized error`() {
        viewModel.nick.value = "NICK"

        `when`(changeNickUseCase.create(viewModel.nick.value!!))
            .thenReturn(Flowable.error(UnauthorizedException(createHttpException(401))))

        viewModel.changeNick()

        viewModel.toastEvent.test().assertValue(R.string.fail_unauthorized)
    }

    @Test
    fun `server error test`() {
        viewModel.nick.value = "NICK"

        `when`(changeNickUseCase.create(viewModel.nick.value!!))
            .thenReturn(Flowable.error(InternalException(createHttpException(500))))

        viewModel.changeNick()

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }
}