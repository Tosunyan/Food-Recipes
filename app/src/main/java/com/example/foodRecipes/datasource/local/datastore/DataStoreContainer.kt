package com.example.foodRecipes.datasource.local.datastore

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

object DataStoreContainer {

    private lateinit var application: Context

    private val Context.dataStore by preferencesDataStore("FoodRecipes-DataStore")

    fun init(application: Application) {
        this.application = application
    }
}