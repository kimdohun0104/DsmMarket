package com.dsm.app

import android.view.View
import androidx.core.content.ContextCompat
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withBackground(background: Int): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("background color: $background")
        }

        override fun matchesSafely(item: View?): Boolean {
            val context = item?.context!!
            val expectedDrawable = ContextCompat.getDrawable(context, background)!!
            return expectedDrawable.constantState == item.background.constantState
        }
    }
}