package com.e.jarvis.models.modelsfavoritos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.jarvis.models.generics.GenericImage
import com.e.jarvis.models.generics.GenericList
import com.e.jarvis.models.generics.GenericResults
import java.io.Serializable

@Entity(tableName = "favoritos")
data class Favorito(
     @PrimaryKey(autoGenerate = false)
    var id: String,
    @Embedded(prefix = "favoritos_")
    val results: GenericResults,
    val tipoDoResult : String
    ) :  Serializable
