package com.coding.scanproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.coding.scanproject.databinding.ActivityDetailMealBinding
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.entity.MealsWrapper
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DetailMealActivity : AppCompatActivity() {

    private val viewModel: DetailMealViewModel by viewModels{MealsDetailViewModelFactory((application as MealsApplication).repository)}

    private lateinit var binding: ActivityDetailMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val idMeal = intent.getStringExtra("idMeal").toString()

        viewModel.getStateDetailMealLiveData().observe(this) { state ->
            when (state){
                is DetailMealState.Success -> {
                    binding.instructionMeal.text = state.meal.strInstructions
                    binding.titleMeal.text = state.meal.strMeal
                    binding.typeMeal.text = state.meal.strCategory
                }
            }
        }

        viewModel.loadStateDetailMealLiveData(idMeal)
    }
}