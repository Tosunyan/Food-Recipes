package com.example.foodRecipes.domain.usecase

import com.example.foodRecipes.datasource.local.datastore.DataStoreContainer
import com.example.foodRecipes.datasource.remote.api.ApiResponse
import com.example.foodRecipes.domain.model.MealModel
import com.example.foodRecipes.domain.repository.HomeRepository
import com.example.foodRecipes.util.DateUtil
import kotlin.time.Duration.Companion.seconds

object GetDailySpecial {

    val EXPIRATION_TIME = 30.seconds

    private val homeRepository = HomeRepository()
    private var randomMealResponse: ApiResponse<MealModel?>? = null

    private val applicationPreferences = DataStoreContainer.applicationPreferences

    suspend fun execute(): ApiResponse<MealModel?> {
        when {
            applicationPreferences.dailySpecial != null && !isTimeExpired() -> {
                randomMealResponse = ApiResponse.Success(applicationPreferences.dailySpecial)
            }
            else -> {
                randomMealResponse = homeRepository.getRandomMeal()
                updatePreferences()
            }
        }

        return randomMealResponse!!
    }

    private fun updatePreferences() {
        applicationPreferences.apply {
            dailySpecialTimestamp = DateUtil.currentTimeMillis
            dailySpecial = (randomMealResponse as? ApiResponse.Success)?.data
        }
    }

    private fun isTimeExpired(): Boolean {
        val dailySpecialTimestamp = applicationPreferences.dailySpecialTimestamp!!
        return DateUtil.currentTimeMillis > dailySpecialTimestamp + EXPIRATION_TIME
    }
}