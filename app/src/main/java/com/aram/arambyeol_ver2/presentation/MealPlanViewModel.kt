package com.aram.arambyeol_ver2.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import com.aram.arambyeol_ver2.core.domain.repository.MealPlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealPlanViewModel @Inject constructor(
    private val repository: MealPlanRepository
): ViewModel() {
    var mealPlan: MutableLiveData<MealPlan> = MutableLiveData<MealPlan>()

    fun getMealPlanToNetwork() {
        viewModelScope.launch {
            val result = repository.getMealPlanToNetwork()
            mealPlan.postValue(result)
        }
    }
}