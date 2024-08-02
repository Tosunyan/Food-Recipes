package com.example.foodRecipes.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodRecipes.domain.model.MealModel
import com.example.foodRecipes.presentation.navigation.NavigationDestination
import com.example.foodRecipes.presentation.navigation.NavigationManager
import com.example.foodRecipes.presentation.theme.components.MealsList
import com.example.foodRecipes.presentation.theme.components.Toolbar
import com.example.foodRecipes.presentation.viewmodel.MealsViewModel

@Composable
fun MealsScreen(
    navigationManager: NavigationManager,
    viewModel: MealsViewModel,
) {
    MealsScreen(
        isLoading = viewModel.isLoading.collectAsState().value,
        title = viewModel.title.collectAsState().value,
        meals = viewModel.meals.collectAsState().value,
        onBackClick = navigationManager::navigateUp,
        onMealClick = {
            val destination = NavigationDestination.MealDetails(mealModel = it)
            navigationManager.navigate(destination)
        }
    )
}

@Composable
fun MealsScreen(
    isLoading: Boolean,
    title: String,
    meals: List<MealModel>,
    onBackClick: () -> Unit = {},
    onMealClick: (MealModel) -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar(
            title = title,
            onBackClick = onBackClick
        )

        MealsList(
            meals = meals,
            isLoading = isLoading,
            onItemClick = onMealClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    MealsScreen(
        isLoading = false,
        title = "Vegetables",
        meals = listOf(
            MealModel(id = "", name = "Potato"),
            MealModel(id = "", name = "Mushroom"),
            MealModel(id = "", name = "Tomato"),
            MealModel(id = "", name = "Cucumber"),
        )
    )
}
