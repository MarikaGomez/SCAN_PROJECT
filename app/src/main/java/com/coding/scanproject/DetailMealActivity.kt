package com.coding.scanproject

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

    private val viewModel: DetailMealViewModel by viewModels()
    private lateinit var binding: ActivityDetailMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val api = retrofit.create(MealsDbApi::class.java)
        val call = api.getRecipeData("52839")

        call.enqueue(object : Callback<MealsWrapper> {
            override fun onResponse(
                call: Call<MealsWrapper>,
                response: Response<MealsWrapper>
            ) {
                val wrapping : MealsWrapper? = response.body()
                val dataList : List<MealsData> = wrapping!!.meals
                val meal : MealsData = dataList[0]
                Log.i("MainActivity", "" + meal)
                val imageUrl : String = meal.strMealThumb
                Log.i("MainActivity", "" + imageUrl)
                Picasso.get().load(imageUrl).into(binding.imageMeal)
                binding.instructionMeal.text = meal.strInstructions
                binding.titleMeal.text = meal.strMeal
                binding.typeMeal.text = meal.strCategory
            }

            override fun onFailure(call: Call<MealsWrapper>, t: Throwable) {
                Log.e("MainActivity", "onFailure: ",t )
            }

        })
        Log.i("MainActivity", retrofit.toString())
        viewModel.getStateDetailMealLiveData().observe(this) { state ->
            when (state){
                is DetailMealState.Success -> {
                    binding.instructionMeal.text = state.meal.strInstructions
                    binding.titleMeal.text = state.meal.strMeal
                    binding.typeMeal.text = state.meal.strCategory
                }
            }
        }

    }
}
