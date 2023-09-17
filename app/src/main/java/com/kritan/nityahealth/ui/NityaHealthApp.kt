package com.kritan.nityahealth.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.kritan.nityahealth.ui.theme.NityaHealthTheme
import kotlinx.coroutines.launch

/**
 * Top-level declarations are kept here
 *
 */
@Composable
fun NityaHealthApp(viewModel: NityaHealthViewModel) {
    NityaHealthTheme {
        val coroutineScope = rememberCoroutineScope()

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        fun openDrawer() {
            coroutineScope.launch {
                drawerState.open()
            }
        }

        fun closeDrawer() {
            coroutineScope.launch {
                drawerState.close()
            }
        }

        fun closeSplashScreen() {
            viewModel.closeSplashScreen()
        }

        NityaHealthNavGraph(
            drawerState = drawerState,
            authState = viewModel.authState,
            openDrawer = ::openDrawer,
            closeDrawer = ::closeDrawer,
            closeSplashScreen = ::closeSplashScreen,
        )

    }
}

