package com.xero.airlineindustry.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "placeList")
data class PlaceListModel(
    @PrimaryKey @ColumnInfo(name = "PlaceId")
    val PlaceId:String,
    val PlaceName:String,
    val CountryId:String,
    val RegionId:String,
    val CityId:String,
    val CountryName:String


)
