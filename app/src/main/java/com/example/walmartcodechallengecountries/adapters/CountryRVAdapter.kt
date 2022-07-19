package com.example.walmartcodechallengecountries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.walmartcodechallengecountries.databinding.CountryItemBinding
import com.example.walmartcodechallengecountries.model.Country

class CountryRVAdapter: RecyclerView.Adapter<CountryRVAdapter.CountryViewHolder>() {

    private val countries = ArrayList<Country>()

    //ViewHolder for Country Item layout.
    inner class CountryViewHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(country: Country){
            binding.tvName.text = country.name
            binding.tvCapital.text = country.capital
            binding.tvRegion.text = country.region
            binding.tvCode.text = country.code
        }

    }

    //Function to set list, each time a change occurs, instead of creation a whole new adapter.
    fun setCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount() = countries.size

}