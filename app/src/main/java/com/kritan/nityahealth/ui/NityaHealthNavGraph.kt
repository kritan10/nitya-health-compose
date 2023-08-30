package com.kritan.nityahealth.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kritan.nityahealth.commons.components.MyDrawer
import com.kritan.nityahealth.feature_auth.authGraph
import com.kritan.nityahealth.feature_consultants.screens.ConsultantsScreen
import com.kritan.nityahealth.feature_dashboard.DashboardScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctorsGraph
import com.kritan.nityahealth.feature_fitness.presentation.exerciseGraph
import com.kritan.nityahealth.feature_user.ProfileScreen
import com.kritan.nityahealth.feature_wellness.screens.WellnessScreen

//define the nav host

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NityaHealthNavGraph(
    drawerState: DrawerState,
    openDrawer: () -> Unit,
    closeDrawer: () -> Unit,
) {
    val navController = rememberNavController()
    val navigationActions = NityaHealthNavigationActions(navController)

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    NavHost(
        navController = navController,
        startDestination = NityaHealthDestinations.AUTH_ROUTE,
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

        doctorsGraph(navController)

        exerciseGraph(navController)

        composable(NityaHealthDestinations.DASHBOARD_ROUTE) {
            MyDrawer(drawerState, closeDrawer, ::navigateTo)
            {
                DashboardScreen(
                    openDrawer = openDrawer,
                    navigateToWellness = navigationActions.navigateToWellness,
                    navigateToConsultants = navigationActions.navigateToConsultants,
                    navigateToNewsArticles = navigationActions.navigateToNewsArticles,
                    navigateToActivities = navigationActions.navigateToActivities,
                    navigateToProfile = navigationActions.navigateToProfile,
                )
            }
        }
        composable(NityaHealthDestinations.PROFILE_ROUTE) {
            ProfileScreen(navigateUp = ::navigateUp)
        }
        composable(NityaHealthDestinations.WELLNESS_ROUTE) {
            WellnessScreen(navigateUp = ::navigateUp)
        }
        composable(NityaHealthDestinations.CONSULTANTS_ROUTE) {
            ConsultantsScreen(navigateUp = ::navigateUp)
        }

    }
}

