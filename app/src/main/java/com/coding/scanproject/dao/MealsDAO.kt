package com.coding.scanproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import com.coding.scanproject.entity.MealsData

@Dao
interface MealsDAO {
    @Query("SELECT * FROM meals_items")
    fun getAll(): LiveData<List<MealsData>>

    @Query("SELECT * FROM meals_items WHERE id LIKE :id")
    fun findById(id: String): MealsData

    @Insert
    fun insertMeal(vararg meal: MealsData)

    @Delete
    fun deleteMeal(meal: MealsData)

    @Update
    fun updateMeal(vararg meal: MealsData)
}