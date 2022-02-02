package com.coding.scanproject

import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.entity.MealsWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbAPI {

    @GET("lookup.php")
    fun getRecipeData (@Query("i")id: String) : Call<MealsWrapper>
}