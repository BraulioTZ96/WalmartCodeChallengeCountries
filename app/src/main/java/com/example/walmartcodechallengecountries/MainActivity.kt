package com.example.walmartcodechallengecountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.walmartcodechallengecountries.adapters.CountryRVAdapter
import com.example.walmartcodechallengecountries.databinding.ActivityMainBinding
import com.example.walmartcodechallengecountries.helpers.ResponseHelper
import com.example.walmartcodechallengecountries.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //View binding of the layout
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //Adapter of the recycler view
    private val countryRVAdapter: CountryRVAdapter by lazy {
        CountryRVAdapter()
    }
    //Viewmodel for ActivityMain view
    private val mainAViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvCountries.adapter = countryRVAdapter
        configureObserver()
    }

    private fun configureObserver(){
        //We are not creating a new adapter each time, we just need to change the list to the new one each time the list changes.
        mainAViewModel.countriesListResult.observe(this) { state ->
            when(state) {
                is ResponseHelper.Loading -> {
                    setVisibilities(pgBarVisibility = View.VISIBLE, rvVisibility = View.GONE, tvExcVisibility = View.GONE)
                }
                is ResponseHelper.Success -> {
                    setVisibilities(pgBarVisibility = View.GONE, rvVisibility = View.VISIBLE, tvExcVisibility = View.GONE)
                    countryRVAdapter.setCountries(state.countries)
                }
                is ResponseHelper.ErrorCode -> {
                    setVisibilities(pgBarVisibility = View.GONE, rvVisibility = View.GONE, tvExcVisibility = View.VISIBLE)
                    binding.tvException.text = state.message
                }
                is ResponseHelper.ErrorException -> {
                    setVisibilities(pgBarVisibility = View.GONE, rvVisibility = View.GONE, tvExcVisibility = View.VISIBLE)
                    binding.tvException.text = state.error.localizedMessage
                }
            }
        }
    }

    private fun setVisibilities(pgBarVisibility: Int, rvVisibility: Int, tvExcVisibility: Int){
        binding.pgBar.visibility = pgBarVisibility
        binding.rvCountries.visibility = rvVisibility
        binding.tvException.visibility = tvExcVisibility
    }

    //Prevent memory leaks.
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}