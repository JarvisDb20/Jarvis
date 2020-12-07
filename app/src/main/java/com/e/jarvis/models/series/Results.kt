package com.e.jarvis.models.series
import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Results (

	@SerializedName("id") val id : String,
	@SerializedName("title") val title : String,
	@SerializedName("description") val description : String,
	@SerializedName("resourceURI") val resourceURI : String,
	@SerializedName("urls") val urls : List<Urls>,
	@SerializedName("startYear") val startYear : String,
	@SerializedName("endYear") val endYear : String,
	@SerializedName("rating") val rating : String,
	@SerializedName("type") val type : String,
	@SerializedName("modified") val modified : String,
	@SerializedName("thumbnail") val thumbnail : ThumbnailSeries,
	@SerializedName("creators") val creators : Creators,
	@SerializedName("characters") val characters : Characters,
	@SerializedName("stories") val stories : Stories,
	@SerializedName("comics") val comics : Comics,
	@SerializedName("events") val events : Events,
	@SerializedName("next") val next : String,
	@SerializedName("previous") val previous : String
)