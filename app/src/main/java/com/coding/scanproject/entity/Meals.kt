package com.coding.scanproject.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

    @Entity
    data class MealsData(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "strMeal") val strMeal: String,
        @ColumnInfo(name = "strCategory") val strCategory: String,
        @ColumnInfo(name = "strArea") val strArea: String,
        @ColumnInfo(name = "strInstructions") val strInstructions: String,
    )
