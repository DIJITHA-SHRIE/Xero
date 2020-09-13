package com.xero.airlineindustry

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xero.airlineindustry.Model.PlaceListModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {


    @Query("SELECT * from placeList")
    fun getPlaceRoomFlow(): Flow<List<PlaceListModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(PlaceListModelModel: List<PlaceListModel>)
}
