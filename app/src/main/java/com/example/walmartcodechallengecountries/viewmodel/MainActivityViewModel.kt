package com.example.walmartcodechallengecountries.viewmodel

import androidx.lifecycle.*
import com.example.walmartcodechallengecountries.helpers.ResponseHelper
import com.example.walmartcodechallengecountries.model.Country
import com.example.walmartcodechallengecountries.repositories.CountryImplementation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val countryRepository: CountryImplementation): ViewModel() {

    private val _countriesListResult: MutableLiveData<ResponseHelper> = MutableLiveData(ResponseHelper.Loading)
    val countriesListResult: LiveData<ResponseHelper> get() = _countriesListResult

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            countryRepository.getAllCountries().collect {
                _countriesListResult.postValue(it)
            }
        }
    }

}