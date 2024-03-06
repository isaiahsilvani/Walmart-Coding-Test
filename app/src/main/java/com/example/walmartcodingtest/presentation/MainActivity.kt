package com.example.walmartcodingtest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmartcodingtest.databinding.ActivityMainBinding
import com.example.walmartcodingtest.presentation.viewmodel.CountriesViewModel
import com.example.walmartcodingtest.presentation.viewmodel.CountriesViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountriesViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = CountriesViewModelFactory().create(CountriesViewModel::class.java)
        setContentView(binding.root)
        initViews()
        initObservers()
        viewModel.getCountriesList()
    }

    private fun initObservers() {
        viewModel.countriesState.observe(this) { countries ->
            countryAdapter.setData(countries)
        }
    }

    private fun initViews() {
        countryAdapter = CountryAdapter()
        binding.rvCountries.apply {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

}