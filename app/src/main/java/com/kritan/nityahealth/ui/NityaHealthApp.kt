package com.kritan.nityahealth.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.kritan.nityahealth.ui.theme.NityaHealthTheme
import kotlinx.coroutines.launch

/**
 * Top-level declarations are kept here
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NityaHealthApp() {
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

            NityaHealthNavGraph(
                drawerState = drawerState,
                openDrawer = ::openDrawer,
                closeDrawer = ::closeDrawer,
            )

    }
}

