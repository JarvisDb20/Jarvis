package com.e.jarvis.models.generics


import androidx.room.*
import com.e.jarvis.models.utils.ApiObject

import java.io.Serializable


@Entity(tableName = "results")
data class GenericResults(
    @PrimaryKey(autoGenerate = false)
    val id: String="",

    val title: String?=null,

    val description: String?=null,

    val resourceURI: String?=null,

    @Embedded(prefix = "series_")
    val series: GenericList?=null,

    @Embedded(prefix = "thumb_")
    val thumbnail: GenericImage?=null,

    @Embedded(prefix = "creators_")
    val creators: GenericList?=null,

    @Embedded(prefix = "chars_")
    val characters: GenericList?=null,

    @Embedded(prefix = "stories_")
    val stories: GenericList?=null,

    @Embedded(prefix = "events_")
    val events: GenericList?=null,

    val name: String?=null,

    @Embedded(prefix = "comics_")
    val comics: GenericList?=null,

    @Embedded(prefix = "apiObject_")
    var apiObject: ApiObject?=null

) : Serializable


