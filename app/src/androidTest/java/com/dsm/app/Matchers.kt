package com.dsm.app

import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
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

fun withViewPagerPage(page: Int) : Matcher<View> {
    return object: TypeSafeMatcher<View>() {
        override fun describeTo(description: Description?) {
            description?.appendText("page : $page")
        }

        override fun matchesSafely(item: View?): Boolean {
            if (item !is  ViewPager2) return false
            return item.currentItem == page
        }

    }
}