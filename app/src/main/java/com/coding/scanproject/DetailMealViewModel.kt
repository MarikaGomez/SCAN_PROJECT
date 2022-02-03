package com.coding.scanproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coding.scanproject.entity.MealsData

sealed class DetailMealState {
    class Error() : DetailMealState()
    class Success(val meal: MealsData) : DetailMealState()
}

class DetailMealViewModel : ViewModel() {

    private var stateMutableLiveData = MutableLiveData<DetailMealState>()
    fun getStateDetailMealLiveData(): LiveData<DetailMealState> = stateMutableLiveData

    //mettre id meal

    fun loadStateDetailMealLiveData(id:String){
        stateMutableLiveData.value = DetailMealState.Success(MealsData(45,"test","test","test","test","test",""))
    }

}