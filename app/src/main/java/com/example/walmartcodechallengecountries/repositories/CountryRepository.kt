package com.example.walmartcodechallengecountries.repositories

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.walmartcodechallengecountries.apis.CountriesAPI
import com.example.walmartcodechallengecountries.model.Country

interface CountryRepository {

    suspend fun getCountries(): List<Country>

}

class CountryImplementation(private val countriesService: CountriesAPI = CountriesAPI.getInstance(), private val contextParent: Context): CountryRepository{

    override suspend fun getCountries(): List<Country> {
        val response = countriesService.getCountries()
        if(response.isSuccessful){
            Toast.makeText(contextParent, "Response successfull", Toast.LENGTH_SHORT).show()
            //Return the response, list of countries if the response was successfull.
            return response.body()!!
        }else{
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
            when(rangeCode){
                4 -> Toast.makeText(contextParent, "Petition not created correctly, be sure to have the last version of the app", Toast.LENGTH_SHORT).show()
                5 -> Toast.makeText(contextParent, "Server broken, we are fixing it, try it later again", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(contextParent, "Unknown error", Toast.LENGTH_SHORT).show()
            }
            //Returns an emptylist in case of error.
            return emptyList()
        }
    }

}