package com.dsm.app.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dsm.domain.usecase.SignUpUseCase
import com.dsm.dsmmarketandroid.presentation.ui.signUp.SignUpViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.Response

class SignUpViewModelTests {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var signUpUseCase: SignUpUseCase
    private lateinit var viewModel: SignUpViewModel

    @Before
    fun init() {
        signUpUseCase = mock(SignUpUseCase::class.java)
        viewModel = SignUpViewModel(signUpUseCase)
    }

    @Test
    fun `when there's no blank isSignUpEnable == true`() {
        val isSignUpEnable = viewModel.isSignUpEnable.test()

        viewModel.email.value = "example@test.com"
        viewModel.password.value = "testPassword"
        viewModel.reType.value = "testPassword"
        viewModel.name.value = "김도훈"
        viewModel.gender.value = "남성"
        viewModel.grade.value = "1"

        assertTrue(isSignUpEnable.value())
    }

    @Test
    fun `when there's blank isSignUpEnable == false`() {
        val isSignUpEnable = viewModel.isSignUpEnable.test()

        viewModel.email.value = "example@test.com"

        assertFalse(isSignUpEnable.value())
    }

    @Test
    fun `when email is invalid`() {
        viewModel.email.value = "example@testcom"

        viewModel.signUp()

        viewModel.toastEmailInvalidEvent.test().assertHasValue()
    }

    @Test
    fun `when password different`() {
        viewModel.email.value = "example@test.com"
        viewModel.password.value = "TEST_PASSWORD"
        viewModel.reType.value = "DIFF_PASSWORD"

        viewModel.signUp()

        viewModel.toastPasswordDiffEvent.test().assertHasValue()
    }

    @Test
    fun `sign up success 200`() {
        viewModel.email.value = "example@test.com"
        viewModel.password.value = "testPassword"
        viewModel.reType.value =  "testPassword"
        viewModel.name.value = "김도훈"
        viewModel.gender.value = "남성"
        viewModel.grade.value = "1"

        val request = hashMapOf(
            "email" to viewModel.email.value,
            "password" to viewModel.password.value,
            "nick" to viewModel.name.value,
            "gender" to viewModel.gender.value,
            "grade" to viewModel.grade.value
        )
        val response = mapOf("message" to 1)
        `when`(signUpUseCase.create(request)).thenReturn(Flowable.just(Response.success(response)))

        viewModel.signUp()

        viewModel.finishActivityEvent.test().assertHasValue()
    }
}