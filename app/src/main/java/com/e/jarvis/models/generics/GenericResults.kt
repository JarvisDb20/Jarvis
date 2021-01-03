package com.e.jarvis.models.generics

import androidx.annotation.Nullable
import androidx.room.*
import com.e.jarvis.models.utils.ConverterType
import java.io.Serializable


@Entity(tableName = "resultsdb")
data class GenericResults(
    @PrimaryKey
      val id: String = " ",
    //  val digitalId: String,
    @Nullable
    val title: String = " ", // story
    //     val issueNumber: String,
    //      val variantDescription: String,
    @Nullable
    val description: String = " ", // story
    //      val modified: String, // story
    //      val isbn: String,
    //       val upc: String,
    //     val diamondCode: String,
    //     val ean: String,
    //      val issn: String,
    //     val format: String,
    //      val pageCount: String,
//    val genericTextObjects : List<GenericTextObject>,
    @Nullable
    val resourceURI: String= " ", // story
//    val urls : List<GenericUrls>,
    @Nullable
    @Embedded(prefix = "algumnome")
    val series: GenericList= GenericList(" ", " ", " "), // story
    //   val variants : List<GenericSummary>,
    //  val collections : List<GenericSummary>,
    //   val collectedIssues : List<GenericSummary>,
    //   val dates : List<GenericDates>,
    //   val prices : List<GenericPrices>,
    @Nullable
    @Embedded(prefix = "algumnome")
    val thumbnail: GenericImage, // story
    //   val genericImages : List<GenericImage>,
    @Nullable
    @Embedded(prefix = "algumnome")
    val creators: GenericList= GenericList(" ", " ", " "), // story
    @Nullable
    @Embedded(prefix = "algumnome")
    val characters: GenericList= GenericList(" ", " ", " "), // story
    @Nullable
    @Embedded(prefix = "algumnome")
    val stories: GenericList= GenericList(" ", " ", " "),
    @Nullable
    @Embedded(prefix = "algumnome")
    val events: GenericList = GenericList(" ", " ", " "), // story
    @Nullable
    val name: String = " ",
    @Nullable
    @Embedded(prefix = "algumnome")
    val comics: GenericList= GenericList(" ", " ", " "), //story
    //     val startYear: String,
    //      val endYear: String,
    //     val rating: String,
    //     val type: String, // story
//    val next : GenericSummary,
//    val previous : GenericSummary,
    //   val originalIssue : GenericSummary // story
) : Serializable