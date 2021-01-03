package com.e.jarvis.models.generics

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class GenericImage(
        @PrimaryKey(autoGenerate = false)
        val path: String,
        val extension: String
) : Serializable
