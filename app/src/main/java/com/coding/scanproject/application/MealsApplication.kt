package com.coding.scanproject.application

import android.app.Application
import com.coding.scanproject.configDatabase.AppDatabase
import com.coding.scanproject.repository.MealsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MealsApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getInstance(this, applicationScope) }
    val repository by lazy { MealsRepository(database.mealsDAO()) }
}