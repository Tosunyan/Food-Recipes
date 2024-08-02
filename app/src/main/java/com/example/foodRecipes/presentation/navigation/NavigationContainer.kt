package com.example.foodRecipes.presentation.navigation

import androidx.compose.runtime.Composable

class NavigationScope(
    manager: NavigationManager,
) {
    val destinations = mutableMapOf<NavigationDestination, @Composable () -> Unit>()

    fun <T : NavigationDestination> addDestination(
        destination: T,
        content: @Composable () -> Unit,
    ) {
        destinations[destination] = content
    }

    fun a() {

    }
}

@Composable
fun NavigationContainer(
    manager: NavigationManager,
    startDestination: NavigationDestination,
    content: NavigationScope.() -> Unit,
) {
    val navigationScope = NavigationScope(manager)
    navigationScope.content()
}

fun <T : NavigationDestination> NavigationScope.destination(
    destination: T,
    content: @Composable () -> Unit,
) {
    addDestination(
        destination = destination,
        content = content
    )
}