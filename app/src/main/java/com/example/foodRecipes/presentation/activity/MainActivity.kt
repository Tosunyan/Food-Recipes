package com.example.foodRecipes.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodRecipes.presentation.navigation.ComposeNavigationImpl
import com.example.foodRecipes.presentation.navigation.NavigationContainer
import com.example.foodRecipes.presentation.navigation.NavigationDestination
import com.example.foodRecipes.presentation.navigation.destination
import com.example.foodRecipes.presentation.screens.BottomNavigationScreen
import com.example.foodRecipes.presentation.screens.MealDetailsScreen
import com.example.foodRecipes.presentation.screens.MealsScreen
import com.example.foodRecipes.presentation.theme.components.BottomNavigation
import com.example.foodRecipes.presentation.theme.indication.ScaleIndicationNodeFactory
import com.inconceptlabs.designsystem.utils.ProvideThemedContent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView()
    }

    /**
     * TODO List
     *  - Encapsulate Compose-Navigation usages with NavigationManager, and Kotlin events
     *  - Finalize NavigationRoutes structure
     *  - Unified solution for custom navigation types
     *  - Bottom navigation appearance
     *  - Change Meals screen Action place/name
     *  - Remove incompetent animations
     *  - MealDetails re-composes multiple times
     *  - Sometime MealDetails doesn't receive the args
     */
    private fun setContentView() {
        ProvideThemedContent(
            indication = ScaleIndicationNodeFactory
        ) {
            val navigationManager = remember(::ComposeNavigationImpl)

            NavigationContainer(
                manager = navigationManager,
                startDestination = NavigationDestination.BottomNavigation,
            ) {
                destination<NavigationDestination.BottomNavigation> {
                    Log.e("TAG", "composable::bottomNavigation")
                    BottomNavigationScreen(
                        navigationManager = navigationManager,
                    )
                }

                destination()
//                    typeMap = mapOf(
//                        typeOf<Action>() to serializableType<Action>()
//                    )
                 {
                    Log.e("TAG", "composable::meals")
//                    val meals = it.toRoute<NavigationDestination.Meals>()
//                    val viewModel = viewModel<MealsViewModel>()
//                    viewModel.onArgumentsReceive(meals)

                    MealsScreen(
                        navigationManager = navigationManager,
                        viewModel = viewModel()
                    )
                }

                destination()
//                    typeMap = mapOf(
//                        typeOf<MealDetailsModel?>() to serializableType<MealDetailsModel?>(),
//                        typeOf<MealModel?>() to serializableType<MealModel?>(),
//                    )
                {
                    Log.e("TAG", "composable::details")
//                    val mealDetails = it.toRoute<NavigationDestination.MealDetails>()
//                    val viewModel = viewModel<MealDetailsViewModel>()
//                    viewModel.onArgumentsReceive(mealDetails)

                    MealDetailsScreen(
                        navigationManager = navigationManager,
                        viewModel = viewModel()
                    )
                }
            }
        }
    }
}