package com.e.jarvis.models


//responsavel por encapsular todas as respostas de serviços
data class ResponseWrapper<out T>(
    val status : Status,
    val data: T?,
    val error: String? = null
){
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
    companion object{
        fun <T> success(data: T?): ResponseWrapper<T> {
            return ResponseWrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResponseWrapper<T> {
            return ResponseWrapper(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResponseWrapper<T> {
            return ResponseWrapper(Status.LOADING, data, null)
        }
    }
}