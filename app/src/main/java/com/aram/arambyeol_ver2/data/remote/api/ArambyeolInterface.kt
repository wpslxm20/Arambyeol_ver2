package com.aram.arambyeol_ver2.data.remote.api

import com.aram.arambyeol_ver2.data.model.MealPlan
import retrofit2.Response
import retrofit2.http.GET

interface ArambyeolInterface {
    @GET("menu")
    suspend fun getMenu(): Response<MealPlan>
}