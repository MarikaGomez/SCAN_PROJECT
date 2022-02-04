package com.coding.scanproject.mealDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.coding.scanproject.application.MealsApplication
import com.coding.scanproject.databinding.ActivityDetailMealBinding

class DetailMealActivity : AppCompatActivity() {

    private val viewModel: DetailMealViewModel by viewModels{ MealsDetailViewModelFactory((application as MealsApplication).repository) }

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