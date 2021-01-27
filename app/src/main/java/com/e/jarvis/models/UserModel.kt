package com.e.jarvis.models

data class UserModel (
    val email : String,
    val password : String? = null,
    val id : String? = null
)
