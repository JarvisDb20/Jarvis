package com.e.jarvis.models.generics

import java.io.Serializable

data class GenericSummary(
    val resourceURI: String,
    val name: String,
    val type: String
) : Serializable