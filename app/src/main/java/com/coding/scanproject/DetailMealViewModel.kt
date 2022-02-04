package com.coding.scanproject

import androidx.lifecycle.*
import com.coding.scanproject.entity.MealsData
import com.coding.scanproject.repository.MealsRepository
import kotlinx.coroutines.launch

sealed class DetailMealState {
    class Error() : DetailMealState()
    class Success(val meal: MealsData) : DetailMealState()
}

class DetailMealViewModel(private val repository: MealsRepository) : ViewModel() {

    private var stateMutableLiveData = MutableLiveData<DetailMealState>()
    fun getStateDetailMealLiveData(): LiveData<DetailMealState> = stateMutableLiveData


    fun loadStateDetailMealLiveData(id:String){
        stateMutableLiveData.value = DetailMealState.Success(repository.findByIdMeal(id))
    }

}

class MealsDetailViewModelFactory(private val repository: MealsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMealViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailMealViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}