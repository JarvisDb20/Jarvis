package com.e.jarvis.models.generics

import java.io.Serializable

data class GenericResults (
    val id : String,
    val digitalId : String,
    val title : String, // story
    val issueNumber : String,
    val variantDescription : String,
    val description : String, // story
    val modified : String, // story
    val isbn : String,
    val upc : String,
    val diamondCode : String,
    val ean : String,
    val issn : String,
    val format : String,
    val pageCount : String,
    val genericTextObjects : List<GenericTextObject>,
    val resourceURI : String, // story
    val urls : List<GenericUrls>,
    val series : GenericList, // story
    val variants : List<GenericSummary>,
    val collections : List<GenericSummary>,
    val collectedIssues : List<GenericSummary>,
    val dates : List<GenericDates>,
    val prices : List<GenericPrices>,
    val thumbnail : GenericImage, // story
    val genericImages : List<GenericImage>,
    val creators : GenericList, // story
    val characters : GenericList, // story
    val stories : GenericList,
    val events : GenericList, // story
    val name : String,
    val comics : GenericList, //story
    val startYear : String,
    val endYear : String,
    val rating : String,
    val type : String, // story
    val next : GenericSummary,
    val previous : GenericSummary,
    val originalIssue : GenericSummary // story
): Serializable