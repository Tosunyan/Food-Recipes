package com.example.foodRecipes.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.foodRecipes.R
import com.example.foodRecipes.domain.model.MealDetailsModel
import com.example.foodRecipes.presentation.navigation.NavigationDestination
import com.example.foodRecipes.presentation.navigation.NavigationManager
import com.example.foodRecipes.presentation.theme.components.MealDetailsList
import com.example.foodRecipes.presentation.viewmodel.SearchViewModel
import com.inconceptlabs.designsystem.components.core.Text
import com.inconceptlabs.designsystem.components.input.InputForm
import com.inconceptlabs.designsystem.theme.AppTheme
import com.inconceptlabs.designsystem.utils.clearFocusOnGesture

@Composable
fun SearchScreen(
    navigationManager: NavigationManager,
    viewModel: SearchViewModel,
) {
    SearchScreen(
        meals = viewModel.meals.collectAsState().value,
        onSearchInputChange = viewModel::onSearchInputChange,
        onMealItemClick = {
            val destination = NavigationDestination.MealDetails(mealDetailsModel = it)
            navigationManager.navigate(destination)
        },
    )
}

@Composable
fun SearchScreen(
    meals: List<MealDetailsModel>,
    onSearchInputChange: (String) -> Unit,
    onMealItemClick: (MealDetailsModel) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 24.dp,
                start = 16.dp,
                end = 16.dp
            )
            .clearFocusOnGesture()
    ) {
        Text(
            text = "Search",
            style = AppTheme.typography.H4
        )

        InputForm(
            hint = stringResource(id = R.string.search_hint),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            onInputChange = onSearchInputChange
        )

        MealDetailsList(
            meals = meals,
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 24.dp),
            onItemClick = onMealItemClick
        )
    }
}