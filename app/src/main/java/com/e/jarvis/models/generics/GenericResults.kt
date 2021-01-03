package com.e.jarvis.models.generics

import androidx.room.*
import java.io.Serializable


@Entity(tableName = "results")
data class GenericResults(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    //  val digitalId: String,

    val title: String?, // story
    //     val issueNumber: String,
    //      val variantDescription: String,
    
    val description: String, // story
    //      val modified: String, // story
    //      val isbn: String,
    //       val upc: String,
    //     val diamondCode: String,
    //     val ean: String,
    //      val issn: String,
    //     val format: String,
    //      val pageCount: String,
//    val genericTextObjects : List<GenericTextObject>,
    
    val resourceURI: String= " ", // story
//    val urls : List<GenericUrls>,

    @Embedded(prefix = "series_")
    val series: GenericList, // story
    //   val variants : List<GenericSummary>,
    //  val collections : List<GenericSummary>,
    //   val collectedIssues : List<GenericSummary>,
    //   val dates : List<GenericDates>,
    //   val prices : List<GenericPrices>,

    @Embedded(prefix = "thumb_")
    val thumbnail: GenericImage, // story
    //   val genericImages : List<GenericImage>,

    @Embedded(prefix = "creators_")
    val creators: GenericList, // story

    @Embedded(prefix = "chars_")
    val characters: GenericList, // story

    @Embedded(prefix = "stories_")
    val stories: GenericList,

    @Embedded(prefix = "events_")
    val events: GenericList, // story

    val name: String?,

    @Embedded(prefix = "comics_")
    val comics: GenericList, //story
    //     val startYear: String,
    //      val endYear: String,
    //     val rating: String,
    //     val type: String, // story
//    val next : GenericSummary,
//    val previous : GenericSummary,
    //   val originalIssue : GenericSummary // story
) : Serializable


