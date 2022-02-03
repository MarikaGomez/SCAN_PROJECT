package com.coding.scanproject.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsWrapper (val meals: List<MealsData>)


@Entity(tableName = "meals_items")
data class MealsData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "idMeal") val idMeal: String,
    @ColumnInfo(name = "strMeal") val strMeal: String,
    @ColumnInfo(name = "strCategory") val strCategory: String,
    @ColumnInfo(name = "strArea") val strArea: String,
    @ColumnInfo(name = "strInstructions") val strInstructions: String,
)
