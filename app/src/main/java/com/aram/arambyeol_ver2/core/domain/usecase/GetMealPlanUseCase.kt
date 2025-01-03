package com.aram.arambyeol_ver2.core.domain.usecase

import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import com.aram.arambyeol_ver2.core.domain.repository.MealPlanRepository
import retrofit2.Response
import javax.inject.Inject

class GetMealPlanUseCase @Inject constructor(
    private val mealPlanRepository: MealPlanRepository
){
    suspend operator fun invoke(): MealPlan {
        return mealPlanRepository.getMealPlanToNetwork()
    }
}