package com.example.foodRecipes.presentation.navigation

import androidx.navigation.NavController
import java.util.Stack

class ComposeNavigationImpl(
    private val navController: NavController,
) : NavigationManager {

    override val backStack: Stack<NavigationDestination>
        get() = TODO("Not yet implemented")

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigate(destination: NavigationDestination) {
        navController.navigate("favorites")
    }

    override fun navigateAndPopBackStackUpTo(
        destination: NavigationDestination,
        popDestination: NavigationDestination,
        inclusive: Boolean,
    ) {

    }

    override fun popBackStackUpTo(
        destination: NavigationDestination,
        inclusive: Boolean
    ) {
        navController.popBackStack()
    }
}