package com.example.foodRecipes.util

import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.milliseconds

object DateUtil {

    val currentTimeMillis: Duration
        get() = System.currentTimeMillis().milliseconds

    val currentDate: Duration
        get() = (currentTimeMillis / 1.days).days
}