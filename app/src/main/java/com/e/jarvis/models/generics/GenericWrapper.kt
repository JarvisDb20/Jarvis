package com.e.jarvis.models.generics

import java.io.Serializable

data class GenericWrapper (
    val code : Int,
    val status : String,
    val copyright : String,
    val attributionText : String,
    val attributionHTML : String,
    val etag : String,
    val data : GenericData
) : Serializable