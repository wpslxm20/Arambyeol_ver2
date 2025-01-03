package com.aram.arambyeol_ver2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import com.aram.arambyeol_ver2.core.domain.repository.MealPlanRepository
import com.aram.arambyeol_ver2.core.domain.usecase.GetMealPlanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealPlanViewModel @Inject constructor(
    private val getMealPlanUseCase: GetMealPlanUseCase
): ViewModel() {
    private val _mealPlan = MutableLiveData<MealPlan>()
    val mealPlan: LiveData<MealPlan> get() = _mealPlan

    fun updateMealPlanToNetwork() {
        viewModelScope.launch {
            val result = getMealPlanUseCase()
            _mealPlan.value = result
        }
    }
}