package com.kritan.nityahealth.ui.components

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.delay


@Composable
fun MyDoubleBackPressForExitBackHandler() {
    var showToast by remember { mutableStateOf(false) }

    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if (showToast) {
        Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        showToast = false
    }


    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(true) {
        when (backPressState) {
            BackPress.Idle -> {
                backPressState = BackPress.InitialTouch
                showToast = true
            }

            BackPress.InitialTouch -> ActivityCompat.finishAffinity(context as Activity)
        }
    }
}

sealed class BackPress {
    object Idle : BackPress()
    object InitialTouch : BackPress()
}