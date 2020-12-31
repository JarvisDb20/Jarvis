package com.e.jarvis.models.generics

import java.io.Serializable


data class GenericList(
        //val id: Int,
        val available: String,
        val returned: String,
        val collectionURI: String,
        // val items: ArrayList<GenericSummary>
) : Serializable