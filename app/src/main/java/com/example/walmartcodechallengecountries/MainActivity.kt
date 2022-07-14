package com.example.walmartcodechallengecountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.walmartcodechallengecountries.adapters.CountryRVAdapter
import com.example.walmartcodechallengecountries.databinding.ActivityMainBinding
import com.example.walmartcodechallengecountries.repositories.CountryImplementation
import com.example.walmartcodechallengecountries.viewmodel.MainActivityViewModel
import com.example.walmartcodechallengecountries.viewmodel.MainActivityViewModelFactory

class MainActivity : AppCompatActivity() {

    //View binding of the layout
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //Adapter of the recycler view
    private lateinit var countryRVAdapter: CountryRVAdapter
    //Viewmodel for ActivityMain view
    lateinit var mainAViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Creating the viewmodel by a factory because needs a parameter.
        mainAViewModel = ViewModelProvider(this, MainActivityViewModelFactory(CountryImplementation(contextParent = this)))[MainActivityViewModel::class.java]
        //Setting the recyclerview adapter.
        countryRVAdapter = CountryRVAdapter()
        binding.rvCountries.adapter = countryRVAdapter
        configureObserver()
    }

    private fun configureObserver(){
        //We are not creating a new adapter each time, we just need to change the list to the new one each time the list changes.
        mainAViewModel.countryListResult.observe(this, Observer { newCountriesList ->
            countryRVAdapter.setCountries(newCountriesList)
            countryRVAdapter.notifyDataSetChanged()
        })
    }

    //Prevent memory leaks.
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}