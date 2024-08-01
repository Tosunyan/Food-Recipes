package com.example.foodRecipes.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MealModel(
    val id: String,
    val name: String = "",
    val thumbnail: String = "",
)