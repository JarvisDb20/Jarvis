package com.e.jarvis.models.utils

import com.e.jarvis.models.generics.GenericImage
import java.io.Serializable

data class ItemImage(val thumb: GenericImage,val apiObject: ApiObject,val name : String = ""):Serializable