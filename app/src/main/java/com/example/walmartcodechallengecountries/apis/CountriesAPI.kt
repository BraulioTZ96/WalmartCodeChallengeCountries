package com.example.walmartcodechallengecountries.apis

import com.example.walmartcodechallengecountries.model.CountryResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesAPI {

    companion object {

        //Base url
        const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"

    }

    //Endpoint to call.
    @GET("countries.json")
    suspend fun getCountries(): Response<CountryResponse>

}