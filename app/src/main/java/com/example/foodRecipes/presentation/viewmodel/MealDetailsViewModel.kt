package com.example.foodRecipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodRecipes.datasource.remote.api.onSuccess
import com.example.foodRecipes.datasource.repository.MealDetailsRepository
import com.example.foodRecipes.domain.mapper.toMealDetailsModel
import com.example.foodRecipes.domain.model.MealDetailsModel
import com.example.foodRecipes.presentation.navigation.NavigationDestination
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MealDetailsViewModel(
    private val repository: MealDetailsRepository = MealDetailsRepository(),
) : ViewModel() {

    private val _mealDetails = MutableStateFlow(MealDetailsModel())
    val mealDetails = _mealDetails.asStateFlow()

    fun onArgumentsReceive(destination: NavigationDestination.MealDetails) {
        when {
            destination.mealDetailsModel != null -> {
                destination.mealDetailsModel
                    .let(::setMealDetails)
            }
            destination.mealModel != null -> {
                destination.mealModel
                    .toMealDetailsModel()
                    .also {
                        setMealDetails(it)
                        getMealDetailsFromApi(it.id)
                    }
            }
        }
    }

    private fun setMealDetails(mealDetails: MealDetailsModel) {
        viewModelScope.launch {
            delay(0.2.seconds) // To make animations work
            _mealDetails.update { mealDetails }
        }
    }

    private fun getMealDetailsFromApi(id: String) {
        viewModelScope.launch {
            delay(0.2.seconds)
            repository
                .getMealDetails(id)
                .onSuccess { _mealDetails.update { toMealDetailsModel() } }
        }
    }
}