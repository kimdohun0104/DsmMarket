package com.dsm.dsmmarketandroid.presentation.util

import java.util.regex.Pattern

object Validator {
    fun validEmail(email: String): Boolean {
        val pattern = Pattern.compile("^[0-9a-zA-Z_]+@[a-zA-z]+[.]+[a-zA-Z.]+$")
        return pattern.matcher(email).find()
    }
}