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
    const val POST_PURCHASE = "post_purchase"
    const val POST_RENT = "post_rent"
    const val CREATE_CHAT_ROOM = "create_chat_room"
    const val COMPLETE_POST = "complete_post"
    const val SEND_TEMP_PASSWORD = "send_temp_password"
    const val CHANGE_NICK = "change_nick"
    const val CHANGE_PASSWORD = "change_password"
    const val CHANGE_LANG = "change_lang"
    const val LOGOUT = "logout"
    const val SEARCH = "search"
    const val SELECT_CATEGORY = "select_category"
    const val SELECT_RELATED = "select_related"
    const val SELECT_RECOMMEND = "select_recommend"

    const val USER_NAME = "user_name"
    const val USER_GRADE = "user_grade"
    const val USER_EMAIL = "user_email"

    const val POST_ID = "post_id"
    const val TITLE = "title"
    const val PRICE = "price"
    const val CATEGORY = "category"

    const val REASON = "reason"
}