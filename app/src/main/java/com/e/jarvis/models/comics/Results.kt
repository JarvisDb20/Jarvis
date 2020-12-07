package com.e.jarvis.models.comics
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Results (

	@SerializedName("id") val id : String,
	@SerializedName("digitalId") val digitalId : String,
	@SerializedName("title") val title : String,
	@SerializedName("issueNumber") val issueNumber : String,
	@SerializedName("variantDescription") val variantDescription : String,
	@SerializedName("description") val description : String,
	@SerializedName("modified") val modified : String,
	@SerializedName("isbn") val isbn : String,
	@SerializedName("upc") val upc : String,
	@SerializedName("diamondCode") val diamondCode : String,
	@SerializedName("ean") val ean : String,
	@SerializedName("issn") val issn : String,
	@SerializedName("format") val format : String,
	@SerializedName("pageCount") val pageCount : String,
	@SerializedName("textObjects") val textObjects : List<TextObject>,
	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("urls") val urls : List<Urls>,
	@SerializedName("series") val series : Series,
	@SerializedName("variants") val variants : List<Variants>,
	@SerializedName("collections") val collections : List<String>,
	@SerializedName("collectedIssues") val collectedIssues : List<String>,
	@SerializedName("dates") val dates : List<Dates>,
	@SerializedName("prices") val prices : List<Prices>,
	@SerializedName("thumbnail") val thumbnail : Thumbnail,
	@SerializedName("images") val images : List<Image>,
	@SerializedName("creators") val creators : Creators,
	@SerializedName("characters") val characters : Characters,
	@SerializedName("stories") val stories : Stories,
//	@SerializedName("events") val events : Events
) : Serializable