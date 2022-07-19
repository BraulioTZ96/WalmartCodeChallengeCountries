package com.example.walmartcodechallengecountries.diHelpers

import com.example.walmartcodechallengecountries.apis.CountriesAPI
import com.example.walmartcodechallengecountries.repositories.CountryImplementation
import com.example.walmartcodechallengecountries.repositories.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceInjection {

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    @Singleton
    fun getNetworkService(okHttpClient: OkHttpClient): CountriesAPI =
        Retrofit.Builder()
            .baseUrl(CountriesAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CountriesAPI::class.java)

    @Provides
    @Singleton
    fun getCountriesRepo(countryService: CountriesAPI): CountryRepository = CountryImplementation(countryService)

}