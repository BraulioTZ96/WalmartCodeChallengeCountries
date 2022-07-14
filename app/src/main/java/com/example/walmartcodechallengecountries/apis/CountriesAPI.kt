package com.example.walmartcodechallengecountries.apis

import com.example.walmartcodechallengecountries.model.CountryResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesAPI {

    //Endpoint to call.
    @GET("countries.json")
    suspend fun getCountries(): Response<CountryResponse>

    companion object {

        //Base url
        private const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
        //Singleton
        private var instance: CountriesAPI? = null

        //Singleton creator, return the instance of the service call.
        fun getInstance(): CountriesAPI{
            if (instance == null){
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(CountriesAPI::class.java)
            }
            return instance!!
        }

    }

}