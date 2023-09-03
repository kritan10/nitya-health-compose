package com.kritan.nityahealth.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kritan.nityahealth.commons.components.MyDrawer
import com.kritan.nityahealth.feature_auth.presentation.authGraph
import com.kritan.nityahealth.feature_consultants.presentation.ConsultantsScreen
import com.kritan.nityahealth.feature_dashboard.presentation.DashboardScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctorsGraph
import com.kritan.nityahealth.feature_exercise.presentation.exerciseGraph
import com.kritan.nityahealth.feature_user.presentation.ProfileScreen
import com.kritan.nityahealth.feature_wellness.presentation.WellnessScreen
import com.kritan.nityahealth.ui.layouts.EmptyScreen
import kotlinx.coroutines.delay

/**
 * The root Navigation Graph i.e. the root [NavHost] holder .
 *
 * It defines the navController to be used throughout the app,
 * the starting destination of the app and route transition animations for each route.
 *
 * Unimplemented routes are filled with an EmptyLayout() composable.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NityaHealthNavGraph(
    viewModel: NityaHealthViewModel = hiltViewModel(),
    drawerState: DrawerState,
    openDrawer: () -> Unit,
    closeDrawer: () -> Unit,
) {
    val navController = rememberNavController()
    val navigationActions = NityaHealthNavigationActions(navController)
    val startDestination = NityaHealthDestinations.AUTH_ROUTE

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    LaunchedEffect(viewModel.mainUiState) {
        delay(1000)
        if (viewModel.mainUiState.auth.isAuth) {
            navigateTo(NityaHealthDestinations.DASHBOARD_ROUTE)
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
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

        composable(
            route = NityaHealthDestinations.DASHBOARD_ROUTE,
            enterTransition = { fadeIn(TweenSpec(400)) },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                )
            },
        ) {
            BackHandler(true) { }
            MyDrawer(
                drawerState,
                viewModel.mainUiState.auth.userName,
                closeDrawer,
                ::navigateTo
            )
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

        //Features left to implement
        composable(NityaHealthDestinations.APPOINTMENT_ROUTE) {
            EmptyScreen(
                title = NityaHealthDestinations.APPOINTMENT_ROUTE,
                navigateUp = ::navigateUp
            )
        }
        composable(NityaHealthDestinations.FOOD_ROUTE) {
            EmptyScreen(
                title = NityaHealthDestinations.FOOD_ROUTE,
                navigateUp = ::navigateUp
            )
        }
        composable(NityaHealthDestinations.YOGA_ROUTE) {
            EmptyScreen(
                title = NityaHealthDestinations.YOGA_ROUTE,
                navigateUp = ::navigateUp
            )
        }
        composable(NityaHealthDestinations.NEWS_ARTICLES_ROUTE) {
            EmptyScreen(
                title = NityaHealthDestinations.NEWS_ARTICLES_ROUTE,
                navigateUp = ::navigateUp
            )

        }
        composable(NityaHealthDestinations.ACTIVITIES_ROUTE) {
            EmptyScreen(
                title = NityaHealthDestinations.ACTIVITIES_ROUTE,
                navigateUp = ::navigateUp
            )

        }
    }
}





