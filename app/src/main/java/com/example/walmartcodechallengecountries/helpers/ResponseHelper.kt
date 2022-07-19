package com.example.walmartcodechallengecountries.helpers

import com.example.walmartcodechallengecountries.model.Country

sealed interface ResponseHelper{
    object Loading : ResponseHelper
    class Success(val countries: List<Country>) : ResponseHelper
    class ErrorCode(val message: String): ResponseHelper
    class ErrorException(val error: Throwable) : ResponseHelper
}