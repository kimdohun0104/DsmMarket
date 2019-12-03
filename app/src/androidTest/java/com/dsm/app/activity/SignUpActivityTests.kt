package com.dsm.app.activity

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.dsm.app.BaseUiTest
import com.dsm.app.MockServerDispatcher
import com.dsm.app.withBackground
import com.dsm.app.withViewPagerPage
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.ui.auth.signUp.SignUpActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpActivityTests : BaseUiTest() {

    @get:Rule
    val activityRule = ActivityTestRule(SignUpActivity::class.java, false, false)

    @Test
    fun nextBtnTest() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_sign_up_email)).perform(typeText("example@example.com"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password)).perform(typeText("hello"), pressImeActionButton())

        onView(withId(R.id.btn_sign_up)).check(matches(withBackground(R.drawable.bg_grey_rounded)))
        onView(withId(R.id.btn_sign_up)).check(matches(not(isClickable())))

        onView(withId(R.id.et_sign_up_password_confirm)).perform(typeText("hello"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).check(matches(withBackground(R.drawable.bg_primary_rounded)))
        onView(withId(R.id.btn_sign_up)).check(matches(isClickable()))
    }

    @Test
    fun nextBtnClickTest() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_sign_up_email)).perform(typeText("example@example.com"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password)).perform(typeText("hello"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password_confirm)).perform(typeText("hello"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withId(R.id.vp_sign_up)).check(matches(withViewPagerPage(1)))
        onView(withId(R.id.btn_sign_up)).check(matches(withText(R.string.sign_up)))
        onView(withId(R.id.btn_sign_up)).check(matches(withBackground(R.drawable.bg_grey_rounded)))
    }

    @Test
    fun invalidEmailTest() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_sign_up_email)).perform(typeText("example@examplecom"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password)).perform(typeText("hello"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password_confirm)).perform(typeText("hello"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withId(R.id.et_sign_up_name)).perform(typeText("name"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withText(R.string.fail_invalid_email)).inRoot(RootMatchers.withDecorView(not(CoreMatchers.`is`(activityRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    @Test
    fun differentPasswordTest() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_sign_up_email)).perform(typeText("example@example.com"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password)).perform(typeText("hello123"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password_confirm)).perform(typeText("hello"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withId(R.id.et_sign_up_name)).perform(typeText("name"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withText(R.string.fail_diff_password)).inRoot(RootMatchers.withDecorView(not(CoreMatchers.`is`(activityRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    @Test
    fun signUpSuccessTest() {
        mockWebServer.dispatcher = MockServerDispatcher.RequestDispatcher()
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_sign_up_email)).perform(typeText("example@example.com"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password)).perform(typeText("hello"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password_confirm)).perform(typeText("hello"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withId(R.id.et_sign_up_name)).perform(typeText("name"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        assertTrue(activityRule.activity.isFinishing)
    }

    @Test
    fun serverErrorTest() {
        mockWebServer.dispatcher = MockServerDispatcher.ErrorDispatcher()
        activityRule.launchActivity(Intent())

        onView(withId(R.id.et_sign_up_email)).perform(typeText("example@example.com"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password)).perform(typeText("hello"), pressImeActionButton())
        onView(withId(R.id.et_sign_up_password_confirm)).perform(typeText("hello"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withId(R.id.et_sign_up_name)).perform(typeText("name"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_up)).perform(click())

        onView(withText(R.string.fail_server_error)).inRoot(RootMatchers.withDecorView(not(CoreMatchers.`is`(activityRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }
}