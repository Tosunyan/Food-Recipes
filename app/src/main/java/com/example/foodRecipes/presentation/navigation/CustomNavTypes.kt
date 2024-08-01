package com.example.foodRecipes.presentation.navigation

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T: Parcelable?> parcelableType(isNullableAllowed: Boolean = true): NavType<T?> {
    return object : NavType<T?>(isNullableAllowed = isNullableAllowed) {

        override fun get(bundle: Bundle, key: String): T? {
            return bundle.getParcelable(key)
        }

        override fun put(bundle: Bundle, key: String, value: T?) {
            bundle.putParcelable(key, value)
        }

        override fun serializeAsValue(value: T?): String {
            return Json.encodeToString(value)
        }

        override fun parseValue(value: String): T {
            return Json.decodeFromString(value)
        }
    }
}

inline fun <reified T> serializableType(
    json: Json = Json,
    isNullableAllowed: Boolean = true
): NavType<T> = object : NavType<T>(isNullableAllowed = isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): T? {
        Log.e("TAG", "serializableType::get, `$key`")
        return bundle.getString(key)?.let(json::decodeFromString)
    }

    override fun parseValue(value: String): T {
        Log.e("TAG", "serializableType::parseValue, `$value`")
        return json.decodeFromString(value)
    }

    override fun serializeAsValue(value: T): String {
        Log.e("TAG", "serializableType::serializeAsValue, `$value`")
        return json.encodeToString(value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        Log.e("TAG", "serializableType::put, `$key`, `$value`")
        bundle.putString(key, json.encodeToString(value))
    }
}