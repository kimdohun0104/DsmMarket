package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.ChangePasswordUseCase
import com.dsm.dsmmarketandroid.presentation.ui.password.changePassword.ChangePasswordViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ChangePasswordViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var changePasswordUseCase: ChangePasswordUseCase
    private lateinit var viewModel: ChangePasswordViewModel

    @Before
    fun init() {
        changePasswordUseCase = mock(ChangePasswordUseCase::class.java)
        viewModel = ChangePasswordViewModel(changePasswordUseCase)
    }

    @Test
    fun `when there's no blank isChangePasswordEnable == true`() {
        viewModel.newPassword.value = "NEW_PASSWORD"
        viewModel.reType.value = "NEW_PASSWORD"

        assertTrue(viewModel.isChangePasswordEnable.test().value())
    }

    @Test
    fun `when there's blank isChangePasswordEnable == false`() {
        viewModel.newPassword.value = "NEW_PASSWORD"

        assertFalse(viewModel.isChangePasswordEnable.test().value())
    }

    @Test
    fun `when password different`() {
        viewModel.newPassword.value = "NEW_PASSWORD"
        viewModel.reType.value = "DIFF_PASSWORD"

        viewModel.changePassword("", 0)

        viewModel.toastPasswordDiffEvent.test().assertHasValue()
    }

    @Test
    fun `change password success (200)`() {
        viewModel.newPassword.value = "NEW_PASSWORD"
        viewModel.reType.value = "NEW_PASSWORD"

        `when`(
            changePasswordUseCase
                .create(
                    mapOf(
                        "email" to "EMAIL@example.com",
                        "authCode" to 0,
                        "password" to viewModel.newPassword.value
                    )
                )
        ).thenReturn(Flowable.just(200))

        viewModel.changePassword("EMAIL@example.com", 0)

        viewModel.finishActivityEvent.test().assertHasValue()
    }

    @Test
    fun `change password failed`() {
        viewModel.newPassword.value = "NEW_PASSWORD"
        viewModel.reType.value = "NEW_PASSWORD"

        `when`(changePasswordUseCase
            .create(
                mapOf(
                    "email" to "EMAIL@example.com",
                    "authCode" to 0,
                    "password" to viewModel.newPassword.value
                )
            )
        ).thenReturn(Flowable.just(400))

        viewModel.changePassword("EMAIL@example.com", 0)

        viewModel.toastServerErrorEvent.test().assertHasValue()
    }
}