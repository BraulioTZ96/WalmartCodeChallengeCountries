package com.example.walmartcodechallengecountries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walmartcodechallengecountries.repositories.CountryImplementation
import java.lang.IllegalArgumentException

class MainActivityViewModelFactory(private val countryRepository: CountryImplementation): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(countryRepository) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }

}