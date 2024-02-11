package com.example.foodRecipes.util

import com.google.gson.Gson
import kotlin.reflect.KClass

private val serializer by lazy {
    Gson()
}

fun <T : Any> String.convertToObject(valueType: KClass<T>): T? {
    return try {
        serializer.fromJson(this, valueType.java)
    } catch (ignore: Exception) {
        null
    }
}

fun Any.convertToJson(): String? {
    return try {
        serializer.toJson(this)
    } catch (ignore: Exception) {
        null
    }
}