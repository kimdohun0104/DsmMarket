package com.dsm.app.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dsm.app.BaseUiTest
import com.dsm.app.withBackground
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.password.forgotPassword.ForgotPasswordActivity
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForgotPasswordActivityTests : BaseUiTest() {

    @get:Rule
    val activityRule = ActivityTestRule(ForgotPasswordActivity::class.java, false, false)

    @Test
    fun sendEmailBtnEnableTest() {
        onView(withId(R.id.et_forgot_password_email)).perform(typeText("example@example.com"),closeSoftKeyboard())

        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(withBackground(R.drawable.bg_primary_rounded)))
        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(isClickable()))
    }

    @Test
    fun sendEmailBtnDisableTest() {
        onView(withId(R.id.et_forgot_password_email)).perform(typeText("example@example.com"), closeSoftKeyboard())

        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(withBackground(R.drawable.bg_primary_rounded)))
        onView(withId(R.id.btn_forgot_password_send_email)).check(matches(isClickable()))
    }

     @Test
     fun emailInvalidTest() {
         onView(withId(R.id.et_forgot_password_email)).perform(typeText("example@examplecom"), closeSoftKeyboard())

         onView(withId(R.id.btn_forgot_password_send_email)).check(matches(withBackground(R.drawable.bg_primary_rounded)))
         onView(withId(R.id.btn_forgot_password_send_email)).check(matches(isClickable()))

         onView(withId(R.id.btn_forgot_password_send_email)).perform(click())

         onView(ViewMatchers.withText(R.string.fail_invalid_email)).inRoot(RootMatchers.withDecorView(not(`is`(activityRule.activity.window.decorView)))).check(matches(ViewMatchers.isDisplayed()))
     }

//    @Test
//    fun
}