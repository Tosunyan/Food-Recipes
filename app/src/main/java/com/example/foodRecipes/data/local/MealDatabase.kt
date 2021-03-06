package com.example.foodRecipes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodRecipes.data.models.Meal

@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao

    companion object {
        private var instance: MealDatabase? = null

        operator fun invoke(context: Context) =
            instance ?: buildDatabase(context).also { instance = it }

        private fun buildDatabase(context: Context) = Room
            .databaseBuilder(
                context.applicationContext,
                MealDatabase::class.java,
                "MealDatabase"
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}