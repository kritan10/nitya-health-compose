package com.kritan.nityahealth.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kritan.nityahealth.commons.components.MyDrawer
import com.kritan.nityahealth.ui.theme.NityaHealthTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NityaHealthApp() {
    NityaHealthTheme {
        val navController = rememberNavController()
        val coroutineScope = rememberCoroutineScope()

        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: NityaHealthDestinations.DASHBOARD_ROUTE


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

        fun navigateTo(route: String) {
            navController.navigate(route)
        }

        MyDrawer(
            drawerState = drawerState,
            currentRoute = currentRoute,
            closeDrawer = ::closeDrawer,
            navigateTo = ::navigateTo
        ) {
            NityaHealthNavGraph(
                navController = navController,
                openDrawer = ::openDrawer,
                navigateTo = ::navigateTo
            )
        }
    }

}

