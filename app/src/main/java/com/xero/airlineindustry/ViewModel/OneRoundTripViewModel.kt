package com.xero.airlineindustry.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.xero.airlineindustry.Model.PlaceInputModel
import com.xero.airlineindustry.Model.PlaceListModel
import com.xero.airlineindustry.Repository.OneRoundTripRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OneRoundTripViewModel internal constructor(
    private val oneRoundTripRepository: OneRoundTripRepository
) : ViewModel() {


    val oneRoundtripUsingFlow: LiveData<List<PlaceListModel>> =
        oneRoundTripRepository.getOneRoundTripWithFlowDAO().asLiveData()


    fun calloneRoundTrip(placeInputModel: PlaceInputModel) {

        launchDataLoad { oneRoundTripRepository.getOneRoundTripWithFlow(placeInputModel) }

    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                block()
            } catch (error: Throwable) {
                Log.i("ViewModelCatch", error.localizedMessage)

            } finally {
                Log.i("FinalViewModel", "error")
            }
        }
    }
}