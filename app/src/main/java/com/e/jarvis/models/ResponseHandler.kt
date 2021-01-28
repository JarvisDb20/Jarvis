package com.e.jarvis.models

import retrofit2.HttpException
import java.net.SocketTimeoutException


open class ResponseHandler {
    enum class ErrorCodes(val code: Int) {
        SocketTimeOut(-1)
    }

    fun <T : Any> handleSuccess(data: T): ResponseWrapper<T> {
        return ResponseWrapper.success(data)
    }

    fun <T : Any> handleLoading(msg: String, data: T? = null): ResponseWrapper<T> {
        return ResponseWrapper.loading(msg, data)
    }
    fun <T : Any> handleLoading(data: T? = null): ResponseWrapper<T> {
        return ResponseWrapper.loading(data=data)
    }

    fun <T : Any> handleException(e: Exception): ResponseWrapper<T> {
        return when (e) {
            is HttpException -> ResponseWrapper.error(getErrorMessage(e.code()))
            is SocketTimeoutException -> ResponseWrapper.error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code)
            )
            else -> ResponseWrapper.error(e.message.toString())
        }
    }
    fun <T : Any> handleException(msg: String): ResponseWrapper<T> {
        return ResponseWrapper.error(msg)
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            400 -> "400 - Bad Request"
            401 -> "401 - Unauthorized"
            402 -> "402 - Payment Required"
            403 -> "403 - Forbidden"
            404 -> "404 - Not Found"
            405 -> "405 - Method Not Allowed"
            406 -> "406 - Not Acceptable"
            407 -> "407 - Proxy Authentication Required"
            408 -> "408 - Request Timeout"
            409 -> "409 - Conflict"
            410 -> "410 - Gone"
            411 -> "411 - Length Required"
            412 -> "412 - Precondition Failed"
            413 -> "413 - Request Entity Too Large"
            414 -> "414 - Request-URI Too Long"
            415 -> "415 - Unsupported Media Type"
            416 -> "416 - Requested Range Not Satisfiable"
            417 -> "417 - Expectation Failed"
            500 -> "500 - Internal Server Error"
            501 -> "501 - Not Implemented"
            502 -> "502 - Bad Gateway"
            503 -> "503 - Service Unavailable"
            504 -> "504 - Gateway Timeout"
            505 -> "505 - HTTP Version Not Supported"
            else -> "Something went wrong"
        }
    }
}