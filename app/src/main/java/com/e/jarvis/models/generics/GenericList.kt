package com.e.jarvis.models.generics

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class GenericList(
       // val id: String?,
        val available: String="",
        val returned: String="",
        val collectionURI: String="",
        // val items: ArrayList<GenericSummary>
) : Serializable