package com.example.foodRecipes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodRecipes.datasource.remote.api.ApiResponse
import com.example.foodRecipes.datasource.remote.data.RegionDto
import com.example.foodRecipes.domain.model.CategoryModel
import com.example.foodRecipes.domain.model.MealModel
import com.example.foodRecipes.domain.usecase.GetCategories
import com.example.foodRecipes.domain.usecase.GetDailySpecial
import com.example.foodRecipes.domain.usecase.GetDailySpecialDuration
import com.example.foodRecipes.domain.usecase.GetRegions
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.time.Duration

class HomeFragmentViewModel : ViewModel() {

    private val dailySpecialResponse = MutableSharedFlow<ApiResponse<MealModel?>>()
    private val categoriesResponse = MutableSharedFlow<ApiResponse<List<CategoryModel>>>()
    private val regionsResponse = MutableSharedFlow<ApiResponse<List<RegionDto>>>()

    val dailySpecial = MutableStateFlow<MealModel?>(null)
    val dailySpecialTimer = MutableStateFlow(Duration.ZERO)

    val categories = MutableStateFlow<List<CategoryModel>>(emptyList())
    val regions = MutableStateFlow<List<RegionDto>>(emptyList())

    val showErrorMessage = MutableSharedFlow<String>()

    init {
        initObservers()
        makeApiCalls()
    }

    private fun makeApiCalls() {
        getDailySpecial()
        getCategories()
        getRegions()
    }

    private fun getDailySpecial() {
        viewModelScope.launch {
            val response = GetDailySpecial.execute()
            dailySpecialResponse.emit(response)

            getDailySpecialDuration()
        }
    }

    private fun getDailySpecialDuration() {
        viewModelScope.launch {
            GetDailySpecialDuration.execute().collect { duration ->
                dailySpecialTimer.value = duration
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            val response = GetCategories.execute()
            categoriesResponse.emit(response)
        }
    }

    private fun getRegions() {
        viewModelScope.launch {
            val response = GetRegions.execute()
            regionsResponse.emit(response)
        }
    }

    private fun initObservers() {
        viewModelScope.launch {
            dailySpecialResponse.collect(::onDailySpecialResponse)
        }

        viewModelScope.launch {
            dailySpecialTimer.collect {
                if (it == Duration.ZERO) {
                    getDailySpecial()
                }
            }
        }

        viewModelScope.launch {
            categoriesResponse.collect(::onCategoriesResponse)
        }

        viewModelScope.launch {
            regionsResponse.collect(::onRegionsResponse)
        }
    }

    private fun onDailySpecialResponse(response: ApiResponse<MealModel?>) {
        when (response) {
            is ApiResponse.Success -> dailySpecial.value = response.data
            is ApiResponse.Failure -> showErrorMessage("Failed to fetch today's meal")
        }
    }

    private fun onCategoriesResponse(response: ApiResponse<List<CategoryModel>>) {
        when (response) {
            is ApiResponse.Success -> categories.value = response.data
            is ApiResponse.Failure -> showErrorMessage("Failed to fetch categories")
        }
    }

    private fun onRegionsResponse(response: ApiResponse<List<RegionDto>>) {
        when (response) {
            is ApiResponse.Success -> regions.value = response.data
            is ApiResponse.Failure -> showErrorMessage("Failed to fetch regions")
        }
    }

    private fun showErrorMessage(message: String) {
        viewModelScope.launch {
            showErrorMessage.emit(message)
        }
    }
}