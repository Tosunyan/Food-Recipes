package com.example.foodRecipes.presentation.navigation

import com.example.foodRecipes.domain.model.MealDetailsModel
import com.example.foodRecipes.domain.model.MealModel
import com.example.foodRecipes.presentation.viewmodel.Action
import kotlinx.serialization.Serializable

interface NavigationDestination {

    @Serializable
    object BottomNavigation: NavigationDestination

    @Serializable
    object Home: NavigationDestination

    @Serializable
    object Search: NavigationDestination

    @Serializable
    object Favorites: NavigationDestination

    @Serializable
    data class Meals(
        val title: String,
        val description: String? = null,
        val action: Action,
    ): NavigationDestination

    @Serializable
    data class MealDetails(
        val mealModel: MealModel? = null,
        val mealDetailsModel: MealDetailsModel? = null,
    ): NavigationDestination
}
