package com.example.walmartcodechallengecountries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.walmartcodechallengecountries.model.Country
import com.example.walmartcodechallengecountries.repositories.CountryImplementation

class MainActivityViewModel(private val countryRepository: CountryImplementation): ViewModel() {

    lateinit var countryListResult: LiveData<List<Country>>

    init {
        getCountries()
    }

    private fun getCountries(){
        countryListResult = liveData {
            val countryList = countryRepository.getCountries()
            emit(countryList)
        }
    }


}