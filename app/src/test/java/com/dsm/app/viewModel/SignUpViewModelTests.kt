package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.error.ErrorEntity
import com.dsm.domain.error.Resource
import com.dsm.domain.usecase.SignUpUseCase
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.auth.signUp.SignUpViewModel
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class SignUpViewModelTests : BaseTest() {

    @Mock
    private lateinit var signUpUseCase: SignUpUseCase

    private lateinit var viewModel: SignUpViewModel

    @Before
    fun init() {
        viewModel = SignUpViewModel(signUpUseCase)
    }

    @Test
    fun `sign up button enable test`() {
        val isSignUpEnable = viewModel.isBtnEnable.test()

        viewModel.email.value = "example@test.com"
        viewModel.password.value = "testPassword"
        viewModel.reType.value = "testPassword"
        viewModel.name.value = "김도훈"
        viewModel.gender.value = "남성"
        viewModel.grade.value = "1"

        assertTrue(isSignUpEnable.value())
    }

    @Test
    fun `sign up button disable test`() {
        val isSignUpEnable = viewModel.isBtnEnable.test()

        viewModel.email.value = "example@test.com"

        assertFalse(isSignUpEnable.value())
    }

    @Test
    fun `when email is invalid`() {
        viewModel.email.value = "example@testcom"

        viewModel.signUp()

        viewModel.toastEvent.test().assertValue(R.string.fail_invalid_email)
    }

    @Test
    fun `when password different`() {
        viewModel.email.value = "example@test.com"
        viewModel.password.value = "TEST_PASSWORD"
        viewModel.reType.value = "DIFF_PASSWORD"

        viewModel.signUp()

        viewModel.toastEvent.test().assertValue(R.string.fail_diff_password)
    }

    @Test
    fun `sign up success test`() {
        viewModel.run {
            email.value = "example@test.com"
            password.value = "testPassword"
            reType.value = "testPassword"
            name.value = "김도훈"
            gender.value = "남성"
            grade.value = "1"

            val request = hashMapOf(
                "email" to viewModel.email.value,
                "password" to viewModel.password.value,
                "nick" to viewModel.name.value,
                "gender" to viewModel.gender.value,
                "grade" to viewModel.grade.value!!.toInt()
            )
            `when`(signUpUseCase.create(request)).thenReturn(Flowable.just(Resource.Success(Unit)))

            signUp()

            showLoadingDialogEvent.test().assertHasValue()
            finishActivityEvent.test().assertHasValue()
            hideLoadingDialogEvent.test().assertHasValue()
        }
    }

    @Test
    fun `sign up internal error test`() {
        viewModel.run {
            email.value = "example@test.com"
            password.value = "testPassword"
            reType.value = "testPassword"
            name.value = "김도훈"
            gender.value = "남성"
            grade.value = "1"

            val request = hashMapOf(
                "email" to viewModel.email.value,
                "password" to viewModel.password.value,
                "nick" to viewModel.name.value,
                "gender" to viewModel.gender.value,
                "grade" to viewModel.grade.value!!.toInt()
            )
            `when`(signUpUseCase.create(request))
                .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500)))))

            signUp()

            showLoadingDialogEvent.test().assertHasValue()
            toastEvent.value = R.string.fail_server_error
            hideLoadingDialogEvent.test().assertHasValue()
        }
    }

    @Test
    fun `sign up exist email test`() {
        viewModel.run {
            email.value = "example@test.com"
            password.value = "testPassword"
            reType.value = "testPassword"
            name.value = "김도훈"
            gender.value = "남성"
            grade.value = "1"

            val request = hashMapOf(
                "email" to viewModel.email.value,
                "password" to viewModel.password.value,
                "nick" to viewModel.name.value,
                "gender" to viewModel.gender.value,
                "grade" to viewModel.grade.value!!.toInt()
            )
            `when`(signUpUseCase.create(request))
                .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500, "{\"message\":\"existent email\"}")))))

            signUp()

            showLoadingDialogEvent.test().assertHasValue()
            toastEvent.value = R.string.fail_existent_email
            hideLoadingDialogEvent.test().assertHasValue()
        }
    }

    @Test
    fun `sign up exist nick test`() {
        viewModel.run {
            email.value = "example@test.com"
            password.value = "testPassword"
            reType.value = "testPassword"
            name.value = "김도훈"
            gender.value = "남성"
            grade.value = "1"

            val request = hashMapOf(
                "email" to viewModel.email.value,
                "password" to viewModel.password.value,
                "nick" to viewModel.name.value,
                "gender" to viewModel.gender.value,
                "grade" to viewModel.grade.value!!.toInt()
            )
            `when`(signUpUseCase.create(request))
                .thenReturn(Flowable.just(Resource.Error(ErrorEntity.Internal(createHttpException(500, "{\"message\":\"existent nick\"}")))))

            signUp()

            showLoadingDialogEvent.test().assertHasValue()
            toastEvent.value = R.string.fail_existent_nick
            hideLoadingDialogEvent.test().assertHasValue()
        }
    }
}