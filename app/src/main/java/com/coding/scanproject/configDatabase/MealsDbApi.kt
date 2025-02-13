package com.coding.scanproject.configDatabase

import com.coding.scanproject.entity.MealsWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsDbApi {

    @GET("lookup.php")
    fun getRecipeData (@Query("i")id: String?) : Call<MealsWrapper>
}