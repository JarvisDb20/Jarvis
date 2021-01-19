package com.e.jarvis.models

import retrofit2.HttpException
import java.net.SocketTimeoutException


open class ResponseHandler {
    enum class ErrorCodes(val code: Int) {
        //para identificar erro internet sem conexão/lenta/DoS
        SocketTimeOut(-1)
    }
    //retorna informação caso nao tenha nenhum erro
    fun <T : Any> handleSuccess(data: T): ResponseWrapper<T> {
        return ResponseWrapper.success(data)
    }

    fun <T : Any> handleException(e: Exception): ResponseWrapper<T> {
        //verifica qual erro deu e de acordo com o erro retorno uma mensagem
        return when (e) {
            //pega os codigos padroes http e retorna
            is HttpException -> ResponseWrapper.error(getErrorMessage(e.code()), null)
            //erro de internet
            is SocketTimeoutException -> ResponseWrapper.error(getErrorMessage(ErrorCodes.SocketTimeOut.code), null)
            //caso seja um erro diferente informa o erro direto do exception
            else -> ResponseWrapper.error(e.message.toString(), null)
        }
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