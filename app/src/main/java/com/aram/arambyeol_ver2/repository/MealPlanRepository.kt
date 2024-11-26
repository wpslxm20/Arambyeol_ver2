package com.aram.arambyeol_ver2.repository

import android.util.Log
import com.aram.arambyeol_ver2.data.model.MealPlan
import com.aram.arambyeol_ver2.data.remote.api.RetrofitObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealPlanRepository {
    suspend fun getMealPlanToNetwork(): MealPlan? {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitObj.retrofitService.getMenu()
                if (response.isSuccessful) {
                    Log.d("getMenu", response.body().toString())
                    response.body()
                } else {
                    Log.d("getMenu", "response is not successful")
                    null
                }
            } catch (e: Exception) {
                Log.e("getMenu", e.message ?: "Unknown error")
                null
            }
        }
    }
}