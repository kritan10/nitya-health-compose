package com.kritan.nityahealth.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kritan.nityahealth.commons.components.MyDrawer
import com.kritan.nityahealth.commons.utils.routes.Routes
import com.kritan.nityahealth.commons.utils.routes.authGraph
import com.kritan.nityahealth.feature_dashboard.DashboardScreen
import com.kritan.nityahealth.feature_profile.ProfileScreen
import com.kritan.nityahealth.ui.theme.NityaHealthTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NityaHealthApp() {
    NityaHealthTheme {
        val mainNavController = rememberNavController()
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

        val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: Routes.Dashboard.route

        NavHost(navController = mainNavController, "app") {
            authGraph(mainNavController)

            composable("app") {
                val appNavController = rememberNavController()
                fun navigateTo(route: Routes) {
                    appNavController.navigate(route.route)
                }
                MyDrawer(
                    drawerState = drawerState,
                    currentRoute = currentRoute,
                    closeDrawer = ::closeDrawer,
                    navigateTo = ::navigateTo
                ) {
                    NavHost(appNavController, Routes.Dashboard.route, enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left
                        )
                    }) {
                        composable(Routes.Dashboard.route) {
                            DashboardScreen(::openDrawer)
                        }
                        composable(Routes.Profile.route) {
                            ProfileScreen(::openDrawer)
                        }
                    }
                }
            }
        }
    }
}

