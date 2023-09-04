package com.kritan.nityahealth.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.ui.theme.NityaHealthTheme
import kotlinx.coroutines.launch

/**
 * Top-level declarations are kept here
 *
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NityaHealthApp(
    viewModel: NityaHealthViewModel = hiltViewModel(),
) {
    NityaHealthTheme {
        val coroutineScope = rememberCoroutineScope()

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        val context = LocalContext.current

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
            authStateFlow = viewModel.authState,
            context = context,
            openDrawer = ::openDrawer,
            closeDrawer = ::closeDrawer,
        )

    }
}

