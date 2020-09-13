package com.xero.airlineindustry.Utils

import android.content.Context
import com.xero.airlineindustry.AppDatabase
import com.xero.airlineindustry.NetworkService
import com.xero.airlineindustry.Repository.OneRoundTripRepository
import com.xero.airlineindustry.UI.OneRoundTripViewModelFactory

interface ViewModelFactoryProvider {

    fun provideViewModelFactory(context: Context): OneRoundTripViewModelFactory

}


val Injector: ViewModelFactoryProvider
    get() = currentInjector

@Volatile
private var currentInjector: ViewModelFactoryProvider =
    DefaultViewModelProvider

private object DefaultViewModelProvider : ViewModelFactoryProvider {
    private fun getRegRepository(context: Context): OneRoundTripRepository {
        return OneRoundTripRepository.getInstance(
            tripDao(context),
            RegService()
        )
    }

    private fun RegService() = NetworkService()

    private fun tripDao(context: Context) =
        AppDatabase.getInstance(context.applicationContext).tripDao()


    override fun provideViewModelFactory(context: Context): OneRoundTripViewModelFactory {
        val repository = getRegRepository(context)
        return OneRoundTripViewModelFactory(repository)
    }
}