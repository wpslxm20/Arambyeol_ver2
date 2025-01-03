package com.aram.arambyeol_ver2.core.domain.entity

import com.aram.arambyeol_ver2.R

enum class MealTimeEnum(val icon: Int, val mealTime: String, val operationTime: String) {
    MORNING(R.drawable.ic_morning, "아침", "08:00~09:00"),
    LUNCH(R.drawable.ic_lunch, "점심", "12:00~13:30"),
    DINNER(R.drawable.ic_dinner, "저녁", "17:30~18:40")
}