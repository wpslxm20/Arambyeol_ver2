package com.aram.arambyeol_ver2.core.domain.api

import com.aram.arambyeol_ver2.core.data.dto.MealPlan
import retrofit2.Response
import retrofit2.http.GET

interface ArambyeolInterface {
    @GET("menu")
    suspend fun getMenu(): Response<MealPlan>
}