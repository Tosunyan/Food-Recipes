package com.example.foodRecipes.presentation.navigation

import java.util.Stack

interface NavigationManager {

    val backStack: Stack<NavigationDestination>

    fun navigateUp()

    fun navigate(destination: NavigationDestination)

    fun navigateAndPopBackStackUpTo(
        destination: NavigationDestination,
        popDestination: NavigationDestination,
        inclusive: Boolean,
    )

    fun popBackStackUpTo(
        destination: NavigationDestination,
        inclusive: Boolean,
    )
}