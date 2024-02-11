package com.example.foodRecipes.datasource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.foodRecipes.domain.model.MealModel
import kotlin.time.Duration

class ApplicationPreferences(
    override val dataStore: DataStore<Preferences>
): DataStoreProvider {

    var dailySpecialTimestamp by ObjectPreference("DAILY_SPECIAL_TIMESTAMP", Duration::class)
    var dailySpecial by ObjectPreference("DAILY_SPECIAL", MealModel::class)
}