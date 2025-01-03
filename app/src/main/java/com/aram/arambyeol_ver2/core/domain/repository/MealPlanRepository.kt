package com.aram.arambyeol_ver2.core.domain.repository

import android.util.Log
import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

interface MealPlanRepository {
    suspend fun getMealPlanToNetwork(): MealPlan
}