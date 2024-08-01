package com.example.foodRecipes.presentation.screens

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodRecipes.R
import com.example.foodRecipes.presentation.navigation.ComposeNavigationImpl
import com.example.foodRecipes.presentation.navigation.NavigationDestination
import com.example.foodRecipes.presentation.navigation.NavigationManager
import com.example.foodRecipes.presentation.theme.components.BottomNavigation
import com.example.foodRecipes.presentation.theme.components.BottomNavigationItem

@Composable
fun BottomNavigationScreen(
    navigationManager: NavigationManager,
) {
    val nestedNavController = rememberNavController()
    val nestedNavigationManager = ComposeNavigationImpl(nestedNavController)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = nestedNavController,
            startDestination = "home",
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            modifier = Modifier
                .weight(1f)
        ) {
            composable("home") {
                Log.e("TAG", "composable::home")
                HomeScreen(
                    navigationManager = navigationManager,
                    viewModel = viewModel()
                )
            }

            composable("search") {
                Log.e("TAG", "composable::search")
                SearchScreen(
                    navigationManager = navigationManager,
                    viewModel = viewModel()
                )
            }

            composable("favorites") {
                Log.e("TAG", "composable::favorites")
                FavoritesScreen(
//                    navigationManager = navigationManager,
                )
            }
        }

        var bottomNavigationItems by remember {
            mutableStateOf(
                listOf(
                    BottomNavigationItem(
                        iconRes = R.drawable.ic_home,
                        destination = NavigationDestination.Home,
                        isSelected = true,
                    ),
                    BottomNavigationItem(
                        iconRes = R.drawable.ic_search,
                        destination = NavigationDestination.Search
                    ),
                    BottomNavigationItem(
                        iconRes = R.drawable.ic_like,
                        destination = NavigationDestination.Favorites
                    )
                )
            )
        }

        BottomNavigation(
            items = bottomNavigationItems,
            onItemClick = { route ->
                val selectedItem = bottomNavigationItems.first(BottomNavigationItem::isSelected)
                val isAlreadySelected = selectedItem.destination == route
                if (isAlreadySelected) return@BottomNavigation

                bottomNavigationItems = bottomNavigationItems.map {
                    it.copy(isSelected = it.destination == route)
                }

                nestedNavigationManager.navigate(route)
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}