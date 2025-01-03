package com.aram.arambyeol_ver2.core.domain.repository

import com.aram.arambyeol_ver2.core.data.dto.MealPlan

interface DataSource {
    suspend fun getMealPlan(): MealPlan
}
