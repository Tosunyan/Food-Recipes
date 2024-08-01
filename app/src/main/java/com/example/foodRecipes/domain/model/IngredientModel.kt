package com.example.foodRecipes.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientModel(
    val name: String,
    val quantity: String
)