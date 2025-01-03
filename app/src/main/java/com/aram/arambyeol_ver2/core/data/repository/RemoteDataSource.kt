package com.aram.arambyeol_ver2.core.data.repository

import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import com.aram.arambyeol_ver2.core.domain.repository.ApiService
import com.aram.arambyeol_ver2.core.domain.repository.DataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : DataSource {
    override suspend fun getMealPlan(): MealPlan {
        return apiService.getMealPlan()
    }
}