package com.kritan.nityahealth.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun MyTimer(
    timeLeft: Int, isPaused: Boolean, onTick: suspend () -> Unit
) {
    LaunchedEffect(key1 = timeLeft, key2 = isPaused) {
        onTick()
    }

    Text(timeParser(timeLeft))
}

private fun timeParser(time: Int): String {
    if (time < 10) {
        return "00:0$time"
    }
    return "00:$time"
}