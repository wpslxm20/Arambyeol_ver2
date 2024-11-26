package com.aram.arambyeol_ver2.di

import com.aram.arambyeol_ver2.repository.MealPlanRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMealPlanRepository(): MealPlanRepository {
        return MealPlanRepository()
    }
}