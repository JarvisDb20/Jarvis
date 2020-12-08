package com.e.jarvis.models.generics

import java.io.Serializable

data class GenericTextObject(
    val type: String,
    val language: String,
    val text: String
) : Serializable