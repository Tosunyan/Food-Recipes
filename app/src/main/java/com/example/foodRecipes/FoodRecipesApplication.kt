package com.example.foodRecipes

import android.app.Application
import com.example.foodRecipes.datasource.local.datastore.DataStoreContainer

class FoodRecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initDataStore()
    }

    private fun initDataStore() {
        DataStoreContainer.init(this)
    }
}