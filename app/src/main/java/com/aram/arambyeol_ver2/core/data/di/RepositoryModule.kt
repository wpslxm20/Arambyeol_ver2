package com.aram.arambyeol_ver2.core.data.di

import com.aram.arambyeol_ver2.core.data.repository.MealPlanRepositoryImpl
import com.aram.arambyeol_ver2.core.domain.repository.MealPlanRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindMealPlanRepository(
        mealPlanRepositoryImpl: MealPlanRepositoryImpl
    ): MealPlanRepository
}