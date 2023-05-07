package com.example.foodRecipes.datasource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.foodRecipes.util.convertToJson
import com.example.foodRecipes.util.convertToObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class Preference<T> {

    operator fun setValue(dataStoreProvider: DataStoreProvider, property: KProperty<*>, value: T) {
        CoroutineScope(Dispatchers.IO).launch {
            set(dataStoreProvider.dataStore, value)
        }
    }

    operator fun getValue(dataStoreProvider: DataStoreProvider, property: KProperty<*>): T {
        return runBlocking {
            get(dataStoreProvider.dataStore)
        }
    }

    protected abstract suspend fun get(dataStore: DataStore<Preferences>): T

    protected abstract suspend fun set(dataStore: DataStore<Preferences>, value: T)
}

class LongPreference(
    private val key: String,
    private val defaultValue: Long,
) : Preference<Long>() {
    override suspend fun get(dataStore: DataStore<Preferences>): Long {
        val flow = dataStore.data.map {
            val preferenceKey = longPreferencesKey(key)
            it[preferenceKey]
        }
        return flow.first() ?: defaultValue
    }

    override suspend fun set(dataStore: DataStore<Preferences>, value: Long) {
        dataStore.edit { preferences ->
            val preferencesKey = longPreferencesKey(key)
            preferences[preferencesKey] = value
        }
    }

}

class ObjectPreference<T : Any>(
    private val key: String,
    private val valueType: KClass<T>
) : Preference<T?>() {

    override suspend fun get(dataStore: DataStore<Preferences>): T? {
        val flow = dataStore.data.map {
            val preferenceKey = stringPreferencesKey(key)
            it[preferenceKey]
        }

        return flow.first()?.convertToObject(valueType)
    }

    override suspend fun set(dataStore: DataStore<Preferences>, value: T?) {
        dataStore.edit { preferences ->
            val preferencesKey = stringPreferencesKey(key)
            preferences[preferencesKey] = value?.convertToJson() ?: ""
        }
    }
}