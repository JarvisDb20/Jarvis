package com.e.jarvis.models.generics


import androidx.room.*
import com.e.jarvis.models.utils.ApiObject

import java.io.Serializable


@Entity(tableName = "results")
data class GenericResults(
    @PrimaryKey(autoGenerate = false)
    val id: String,

    val title: String?,

    val description: String?,

    val resourceURI: String? = " ",

    @Embedded(prefix = "series_")
    val series: GenericList?,

    @Embedded(prefix = "thumb_")
    val thumbnail: GenericImage?,

    @Embedded(prefix = "creators_")
    val creators: GenericList?,

    @Embedded(prefix = "chars_")
    val characters: GenericList?,

    @Embedded(prefix = "stories_")
    val stories: GenericList?,

    @Embedded(prefix = "events_")
    val events: GenericList?,

    val name: String?,

    @Embedded(prefix = "comics_")
    val comics: GenericList?,

    @Embedded(prefix = "apiObject_")
    var apiObject: ApiObject?

) : Serializable


