package com.dsm.dsmmarketandroid.presentation.util

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object Analytics {

    fun logEvent(context: Context, event: String, bundle: Bundle?) {
        FirebaseAnalytics.getInstance(context).logEvent(event, bundle)
    }

    const val SIGN_UP = "sign_up"
    const val LOGIN = "login"
    const val PURCHASE_DETAIL = "purchase_detail"
    const val RENT_DETAIL = "rent_detail"
    const val INTEREST_PURCHASE = "interest_purchase"
    const val INTEREST_RENT = "interest_rent"
    const val GET_COMMENT = "get_comment"
    const val ADD_COMMENT = "add_comment"
    const val REPORT_COMMENT = "report_comment"
    const val REPORT_POST = "report_post"

    const val USER_NAME = "user_name"
    const val USER_GRADE = "user_grade"
    const val USER_EMAIL = "user_email"

    const val POST_ID = "post_id"

    const val REASON = "reason"
}