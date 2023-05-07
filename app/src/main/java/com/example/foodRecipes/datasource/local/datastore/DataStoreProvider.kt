package com.example.foodRecipes.datasource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences

interface DataStoreProvider {

    val dataStore: DataStore<Preferences>
}