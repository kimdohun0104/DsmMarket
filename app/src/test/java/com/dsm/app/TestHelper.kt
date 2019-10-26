package com.dsm.app

import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

fun createHttpException(errorCode: Int): HttpException {
    val errorResponse: Response<Unit> = Response.error(errorCode,
        ResponseBody.create(MediaType.parse("application/json"), ""))

    return HttpException(errorResponse)
}

fun createHttpException(errorCode: Int, json: String): HttpException {
    val errorResponse: Response<Unit> = Response.error(errorCode,
        ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), json))

    return HttpException(errorResponse)
}