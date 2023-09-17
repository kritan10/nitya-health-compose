package com.kritan.nityahealth.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point of the app.
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: NityaHealthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isLoading.value }
        }
        setContent {
            NityaHealthApp(viewModel)
        }
    }
}
