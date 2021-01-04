package com.e.jarvis.models.modelsfavoritos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.e.jarvis.models.generics.GenericImage
import com.e.jarvis.models.generics.GenericList
import com.e.jarvis.models.generics.GenericResults
import java.io.Serializable

@Entity(tableName = "charsfavoritos")
data class CharFavorito(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String?, // story
    var description: String?, // story
    val resourceURI: String? = " ", // story
    @Embedded(prefix = "series_")
    val series: GenericList?, // story
    @Embedded(prefix = "thumb_")
    val thumbnail: GenericImage, // story
    @Embedded(prefix = "creators_")
    val creators: GenericList?, // story
    @Embedded(prefix = "chars_")
    val characters: GenericList?, // story
    @Embedded(prefix = "stories_")
    val stories: GenericList?,
    @Embedded(prefix = "events_")
    val events: GenericList?, // story
    var name: String?,
    @Embedded(prefix = "comics_")
    val comics: GenericList?, //story
) :  Serializable
