package com.e.jarvis.models.generics

import java.io.Serializable


data class GenericData (
    val offset : Int,
    val limit : Int,
    val total : Int,
    val count : Int,
    val results : List<GenericResults>
    ) : Serializable
