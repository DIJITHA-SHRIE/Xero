package com.xero.airlineindustry.Utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.xero.airlineindustry.Model.PlaceListModel

class Converters {

    @TypeConverter
    fun listToJson(value: List<PlaceListModel>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<PlaceListModel>::class.java).toList()
}