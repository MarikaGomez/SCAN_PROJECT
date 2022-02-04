package com.coding.scanproject

import androidx.lifecycle.*
import com.coding.scanproject.configDatabase.AppDatabase
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.repository.MealsRepository
import kotlinx.coroutines.launch

class MealsListViewModel(private val repository: MealsRepository): ViewModel() {

    private val mealsMutableLiveData = MutableLiveData<List<MealsData>>()
    private val mealsMutable = mutableListOf<MealsData>()
//    private var readAllMeals : LiveData<List<MealsData>>

    val allMeals: LiveData<List<MealsData>> = repository.mealsList

//    init {
//        val mealDB = AppDatabase.getInstance().mealsDAO()
//        repository = MealsRepository(mealDB)
////        readAllMeals = repository.getAllMeals()
//    }

    fun loadMeals() {
        insertMeal(MealsData(87889989, "dddd", "ddddd", "ddddd", "ddddd"))
    }

    fun getMeals() : LiveData<List<MealsData>> = repository.getAllMeals()

    fun insertMeal(meal: MealsData) = viewModelScope.launch {
        repository.insertMeal(meal)
    }
}

class MealsListViewModelFactory(private val repository: MealsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

