package com.aram.arambyeol_ver2.core.domain.repository

import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import retrofit2.http.GET

interface ApiService {
    @GET("menu")
    suspend fun getMealPlan(): MealPlan
}