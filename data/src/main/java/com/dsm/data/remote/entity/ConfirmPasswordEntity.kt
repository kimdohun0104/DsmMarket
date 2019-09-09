package com.dsm.data.remote.entity

data class ConfirmPasswordEntity(
    val authCode: Int,

    val email: String
)