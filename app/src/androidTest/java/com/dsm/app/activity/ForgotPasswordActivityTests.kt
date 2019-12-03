package com.dsm.app.activity

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dsm.app.BaseUiTest
import com.dsm.app.MockServerDispatcher
import com.dsm.app.withBackground
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.auth.forgotPassword.ForgotPasswordActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForgotPasswordActivityTests : BaseUiTest() {

    @get:Rule
    val activityRule = ActivityTestRule(ForgotPasswordActivity::class.java, false, false)

    @Test
    fun sendEmailBtnTest() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(withBackground(R.drawable.bg_grey_rounded)))
        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(not(isClickable())))

        onView(withId(R.id.et_forgot_password_email)).perform(typeText("example@example.com"),closeSoftKeyboard())

        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(withBackground(R.drawable.bg_primary_rounded)))
        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(isClickable()))
    }

     @Test
     fun emailInvalidTest() {
         activityRule.launchActivity(Intent())

         onView(withId(R.id.et_forgot_password_email)).perform(typeText("example@examplecom"), closeSoftKeyboard())

         onView(withId(R.id.btn_forgot_password_send_email)).perform(click())

         onView(withText(R.string.fail_invalid_email)).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView)))).check(matches(isDisplayed()))
     }

    @Test
    fun sendEmailSuccessTest() {
        mockWebServer.dispatcher = MockServerDispatcher.RequestDispatcher()
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_forgot_password_email)).perform(typeText("example@example.com"), closeSoftKeyboard())

        onView(withId(R.id.btn_forgot_password_send_email)).perform(click())

        assertTrue(activityRule.activity.isFinishing)
    }

    @Test
    fun serverErrorTest() {
        mockWebServer.dispatcher = MockServerDispatcher.ErrorDispatcher()
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_forgot_password_email)).perform(typeText("example@example.com"), closeSoftKeyboard())

        onView(withId(R.id.btn_forgot_password_send_email)).perform(click())

        onView(withText(R.string.fail_server_error)).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }
}