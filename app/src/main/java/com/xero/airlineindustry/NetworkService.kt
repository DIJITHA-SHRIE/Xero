package com.xero.airlineindustry

import android.util.Log
import com.xero.airlineindustry.Model.PlaceInputModel
import com.xero.airlineindustry.Model.PlaceObjectModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

class NetworkService {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val airLineService = retrofit.create(AirLineService::class.java)

    @ExperimentalCoroutinesApi
    suspend fun placeService(placeInputModel: PlaceInputModel): PlaceObjectModel =

        withContext(Dispatchers.Default) {
            delay(1500)
            val result = airLineService.getPlaceList(placeInputModel.query)
            try {
                Log.i("resultMessage", result.Places.get(0).CountryName)
            } catch (e: Exception) {
                Log.i("resultError", e.localizedMessage)

            }
            result
        }

}

interface AirLineService {
    @Headers(
        "x-rapidapi-host: skyscanner-skyscanner-flight-search-v1.p.rapidapi.com",
        "x-rapidapi-key: ad24cc2b1emsh966ffecba79c3c9p168b82jsnb0e517d74b51"
    )
    @GET("autosuggest/v1.0/UK/GBP/en-GB/")
    suspend fun getPlaceList(@Query("query") query: String): PlaceObjectModel


}