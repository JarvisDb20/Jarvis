package com.e.jarvis.models

data class LoginModel (
    val user : String,
    val password : String? = null,
    val id : String? = null
)
