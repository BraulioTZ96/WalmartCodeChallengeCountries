package com.example.walmartcodechallengecountries.repositories

import com.example.walmartcodechallengecountries.apis.CountriesAPI
import com.example.walmartcodechallengecountries.helpers.ResponseHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CountryRepository {
    fun getAllCountries(): Flow<ResponseHelper>
}

class CountryImplementation @Inject constructor(
    private val countriesService: CountriesAPI
): CountryRepository{

    override fun getAllCountries(): Flow<ResponseHelper> =
        flow {
            emit(ResponseHelper.Loading)
            try {
                val response = countriesService.getCountries()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResponseHelper.Success(it))
                    }
                } else {
                    //Code to determine the range of the response code, it will help us no determine which message display.
                    var rangeCode = 0
                    if(response.code() in 300..399){
                        rangeCode = 3
                    }else if (response.code() in 400..499){
                        rangeCode = 4
                    }else if(response.code() in 500..599){
                        rangeCode = 5
                    }
                    //Display message by range code, it will help us to set a proper message for a range of codes.
                    val errorMessageCode = when(rangeCode){
                        4 -> "Petition not created correctly, be sure to have the last version of the app"
                        5 -> "Server broken, we are fixing it, try it later again"
                        else -> "Unknown Code error, please contact us with the next code: ${response.code()}"
                    }
                    emit(ResponseHelper.ErrorCode(errorMessageCode))
                }
            } catch (e: Exception) {
                emit(ResponseHelper.ErrorException(e))
            }
        }

}