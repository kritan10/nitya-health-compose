package com.kritan.nityahealth.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kritan.nityahealth.feature_auth.authGraph
import com.kritan.nityahealth.feature_dashboard.DashboardScreen
import com.kritan.nityahealth.feature_user.ProfileScreen
import com.kritan.nityahealth.feature_wellness.screens.WellnessScreen

//define the nav host

@Composable
fun NityaHealthNavGraph(
    navController: NavHostController,
    openDrawer: () -> Unit,
    navigateTo: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NityaHealthDestinations.DASHBOARD_ROUTE,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right
            )
        },
    ) {

        authGraph(navController)

        composable(NityaHealthDestinations.DASHBOARD_ROUTE) {
            DashboardScreen(openDrawer, navigateTo)
        }
        composable(NityaHealthDestinations.PROFILE_ROUTE) {
            ProfileScreen(openDrawer)
        }
        composable(NityaHealthDestinations.WELLNESS_ROUTE){
            WellnessScreen(openDrawer)

        }
    }
}

