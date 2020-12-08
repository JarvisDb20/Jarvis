package com.e.jarvis.models.generics

import java.io.Serializable

data class GenericList (
    val available : String,
    val returned : String,
    val collectionURI : String,
    val items : ArrayList<GenericSummary>
): Serializable