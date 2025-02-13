package com.coding.scanproject.mealList

import androidx.lifecycle.*
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.repository.MealsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsListViewModel(private val repository: MealsRepository): ViewModel() {
    private val mealsMutableLiveData = MutableLiveData<List<MealsData>>()
    private val mealsMutable = mutableListOf<MealsData>()

    val allMeals: LiveData<List<MealsData>> = repository.mealsList

    fun getMeals() : LiveData<List<MealsData>> = repository.getAllMeals()

    fun insertMeal(meal: MealsData) = viewModelScope.launch {
        repository.insertMeal(meal)
    }
    fun deleteMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData()
        }
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
