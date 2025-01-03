package com.aram.arambyeol_ver2.core.data.repository

import android.util.Log
import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import com.aram.arambyeol_ver2.core.domain.api.RetrofitObj
import com.aram.arambyeol_ver2.core.domain.repository.MealPlanRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class MealPlanRepositoryImpl @Inject constructor(
) : MealPlanRepository {
    override suspend fun getMealPlanToNetwork(): Response<MealPlan> {
        return RetrofitObj.retrofitService.getMenu()
    }
}