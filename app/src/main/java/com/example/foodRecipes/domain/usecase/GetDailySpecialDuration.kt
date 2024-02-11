package com.example.foodRecipes.domain.usecase

import com.example.foodRecipes.datasource.local.datastore.DataStoreContainer
import com.example.foodRecipes.util.DateUtil
import com.example.foodRecipes.util.createCountDownTimer
import com.example.foodRecipes.util.logApiRequest
import com.example.foodRecipes.util.logInfo
import kotlinx.coroutines.flow.Flow
import kotlin.time.Duration

object GetDailySpecialDuration {

    fun execute(): Flow<Duration> {
        val dailySpecialTimestamp = DataStoreContainer.applicationPreferences.dailySpecialTimestamp

        logInfo(dailySpecialTimestamp.toString())
        val initialTime = dailySpecialTimestamp!! + GetDailySpecial.EXPIRATION_TIME - DateUtil.currentTimeMillis
        return createCountDownTimer(initialTime)
    }
}