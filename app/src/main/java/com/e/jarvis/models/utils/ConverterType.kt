package com.e.jarvis.models.utils

import androidx.annotation.Nullable
import androidx.room.TypeConverter
import com.e.jarvis.models.generics.GenericImage
import com.e.jarvis.models.generics.GenericList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterType {

    val gson = Gson()

    //GenericImage
    @TypeConverter
    fun genericImageToString(genericImage: GenericImage): String {
        return gson.toJson(genericImage)
    }

    @TypeConverter
    fun stringToGenericImage(data: String): GenericImage {
        val genericImageRetorno = object : TypeToken<GenericImage>() {}.type
        return gson.fromJson(data, genericImageRetorno)
    }

    //GenericList
    @Nullable
    @TypeConverter
    fun genericListToString(genericList: GenericList?): String {
        return gson.toJson(genericList)
    }

    @Nullable
    @TypeConverter
    fun stringToGenericList(dataList: String): GenericList? {
        val genericListRetorno = object : TypeToken<GenericList>() {}.type
        return gson.fromJson(dataList, genericListRetorno)
    }


}