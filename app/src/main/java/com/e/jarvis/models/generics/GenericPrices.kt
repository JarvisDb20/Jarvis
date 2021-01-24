package com.e.jarvis.models.generics

import java.io.Serializable

data class GenericPrices(
    val type: String,
    val price: String
) : Serializable
