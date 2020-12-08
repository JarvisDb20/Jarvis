package com.e.jarvis.models.generics

import java.io.Serializable

data class GenericImage(
    val path: String,
    val extension: String
) : Serializable
