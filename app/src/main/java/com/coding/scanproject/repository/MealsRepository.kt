package com.coding.scanproject.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.coding.scanproject.dao.MealsDAO
import com.coding.scanproject.entity.MealsData

class MealsRepository(private val mealsDAO: MealsDAO) {
    val mealsList : LiveData<List<MealsData>> = mealsDAO.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMeal(meal: MealsData) {
        mealsDAO.insertMeal(meal)
    }

    fun getAllMeals(): LiveData<List<MealsData>> = mealsDAO.getAll()

    fun findByIdMeal(id:String): MealsData = mealsDAO.findById(id)

}