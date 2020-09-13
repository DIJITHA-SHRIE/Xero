package com.xero.airlineindustry.Repository

import com.xero.airlineindustry.Model.PlaceInputModel
import com.xero.airlineindustry.Model.PlaceListModel
import com.xero.airlineindustry.NetworkService
import com.xero.airlineindustry.TripDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class OneRoundTripRepository private constructor(
    private val tripDao: TripDao,
    private val networkService: NetworkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    suspend fun getOneRoundTripWithFlow(placeInputModel: PlaceInputModel) {
        val regResult = networkService.placeService(placeInputModel)
        tripDao.insertAll(regResult.Places)

    }

    fun getOneRoundTripWithFlowDAO(): Flow<List<PlaceListModel>> {
        return tripDao.getPlaceRoomFlow()

    }


    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: OneRoundTripRepository? = null

        fun getInstance(apecDao: TripDao, plantService: NetworkService) =
            instance ?: synchronized(this) {
                instance ?: OneRoundTripRepository(apecDao, plantService).also { instance = it }
            }
    }
}