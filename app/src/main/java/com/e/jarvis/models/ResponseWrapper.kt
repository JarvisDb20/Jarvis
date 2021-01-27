package com.e.jarvis.models



data class ResponseWrapper<out T>(
    val status: Status,
    val data: T?,
    val error: String? = null,
    val msg: String? = null
) {
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): ResponseWrapper<T> {
            return ResponseWrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): ResponseWrapper<T> {
            return ResponseWrapper(Status.ERROR, null, msg)
        }

        fun <T> loading(msg: String? = null, data: T?): ResponseWrapper<T> {
            return ResponseWrapper(Status.LOADING, data, msg = msg)
        }
    }
}