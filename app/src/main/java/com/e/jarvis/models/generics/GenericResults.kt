package com.e.jarvis.models.generics

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.e.jarvis.models.utils.ConverterType
import java.io.Serializable


@Entity(tableName = "resultsdb")
data class GenericResults(
        @PrimaryKey(autoGenerate = true)
        val idbancodados: Int,
        val id: String,
        val digitalId: String,
        val title: String, // story
        val issueNumber: String,
        val variantDescription: String,
        val description: String, // story
        val modified: String, // story
        val isbn: String,
        val upc: String,
        val diamondCode: String,
        val ean: String,
        val issn: String,
        val format: String,
        val pageCount: String,
//    val genericTextObjects : List<GenericTextObject>,
        val resourceURI: String, // story
//    val urls : List<GenericUrls>,
        @TypeConverters(ConverterType::class)
        val series: GenericList, // story
        //   val variants : List<GenericSummary>,
        //  val collections : List<GenericSummary>,
        //   val collectedIssues : List<GenericSummary>,
        //   val dates : List<GenericDates>,
        //   val prices : List<GenericPrices>,
        @TypeConverters(ConverterType::class)
        val thumbnail: GenericImage, // story
        //   val genericImages : List<GenericImage>,
        @TypeConverters(ConverterType::class)
        val creators: GenericList, // story
        @TypeConverters(ConverterType::class)
        val characters: GenericList, // story
        @TypeConverters(ConverterType::class)
        val stories: GenericList,
        @TypeConverters(ConverterType::class)
        val events: GenericList, // story
        val name: String,
        @TypeConverters(ConverterType::class)
        val comics: GenericList, //story
        val startYear: String,
        val endYear: String,
        val rating: String,
        val type: String, // story
//    val next : GenericSummary,
//    val previous : GenericSummary,
        //   val originalIssue : GenericSummary // story
) : Serializable