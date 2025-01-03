package com.aram.arambyeol_ver2.core.data.repository

import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import com.aram.arambyeol_ver2.core.domain.repository.ApiService
import com.aram.arambyeol_ver2.core.domain.repository.MealPlanRepository
import retrofit2.Response
import javax.inject.Inject

class MealPlanRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MealPlanRepository {
    override suspend fun getMealPlanToNetwork(): MealPlan {
        return apiService.getMealPlan()
    }
}