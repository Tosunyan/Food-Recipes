package com.example.foodRecipes.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

fun createCountDownTimer(
    duration: Duration,
    intervalTime: Duration = 1.seconds
) = flow {
    var remainingTime = duration

    while (remainingTime > Duration.ZERO) {
        emit(remainingTime)
        delay(intervalTime)
        remainingTime -= intervalTime
    }
}.onCompletion {
    emit(Duration.ZERO)
}

fun Duration.toFormattedString(
    includeDays: Boolean = true,
    includeHours: Boolean = true,
    includeMinutes: Boolean = true,
    includeSeconds: Boolean = true,
    separator: String = ":"
) = this.toComponents { days, hours, minutes, seconds, _ ->
    val hasDays = days > 0
    val hasHours = hours > 0
    val hasMinutes = minutes > 0
    val hasSeconds = seconds > 0

    val components = mutableListOf<String>()

    if (includeDays && hasDays) {
        components += "$days"
    }
    if (includeHours && hasHours || (hasDays && (hasMinutes || hasSeconds))) {
        components += "$hours"
    }
    if (includeMinutes && hasMinutes || (hasSeconds && (hasHours || hasDays))) {
        components += "$minutes"
    }
    if (includeSeconds) {
        components += "$seconds"
    }

    components.joinToString(separator)
}