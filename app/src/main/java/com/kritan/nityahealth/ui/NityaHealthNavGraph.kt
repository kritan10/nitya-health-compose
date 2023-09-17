package com.kritan.nityahealth.ui

import android.util.Log
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kritan.nityahealth.auth.data.models.AuthState
import com.kritan.nityahealth.auth.presentation.authGraph
import com.kritan.nityahealth.base.screens.IntroScreen
import com.kritan.nityahealth.feature_consultants.presentation.ConsultantsScreen
import com.kritan.nityahealth.feature_dashboard.presentation.DashboardScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctorsGraph
import com.kritan.nityahealth.feature_exercise.presentation.exerciseGraph
import com.kritan.nityahealth.feature_onboarding.presentation.onboardingGraph
import com.kritan.nityahealth.feature_user.presentation.ProfileScreen
import com.kritan.nityahealth.feature_wellness.presentation.WellnessScreen
import com.kritan.nityahealth.ui.components.MyDoubleBackPressForExitBackHandler
import com.kritan.nityahealth.ui.components.MyDrawer
import com.kritan.nityahealth.ui.layouts.EmptyScreen
import com.kritan.nityahealth.ui.theme.myEnterTransition
import com.kritan.nityahealth.ui.theme.myExitTransition
import com.kritan.nityahealth.ui.theme.myFadeEnterTransition
import com.kritan.nityahealth.ui.theme.myFadeExitTransition
import com.kritan.nityahealth.ui.theme.myPopEnterTransition
import com.kritan.nityahealth.ui.theme.myPopExitTransition
import kotlinx.coroutines.flow.StateFlow

/**
 * The root Navigation Graph i.e. the root [NavHost] holder .
 *
 * It defines the navController to be used throughout the app,
 * the starting destination of the app and route transition animations for each route.
 *
 * Unimplemented routes are filled with an EmptyLayout() composable.
 */

@Composable
fun NityaHealthNavGraph(
    drawerState: DrawerState,
    authState: StateFlow<AuthState>,
    openDrawer: () -> Unit,
    closeDrawer: () -> Unit,
    closeSplashScreen: () -> Unit,
) {
    val navController = rememberNavController()
    val navigationActions = NityaHealthNavigationActions(navController)
    val startDestination = NityaHealthDestinations.INTRO_ROUTE
    val auth by authState.collectAsState()

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { myEnterTransition() },
        exitTransition = { myExitTransition() },
        popEnterTransition = { myPopEnterTransition() },
        popExitTransition = { myPopExitTransition() },
    ) {

        introScreen()

        onboardingGraph(
            navController = navController,
            navigateToAuthAndClearBackStack = navigationActions.navigateToAuthAndClearBackStack
        )

        authGraph(
            navController = navController,
            navigateToDashboardAndClearBackStack = navigationActions.navigateToDashboardAndClearBackStack
        )

        dashboard(
            drawerState = drawerState,
            userName = auth.userName,
            navigationActions = navigationActions,
            closeDrawer = closeDrawer,
            navigateTo = ::navigateTo,
            openDrawer = openDrawer,
        )

        doctorsGraph(
            navController = navController,
            navigateToSignIn = navigationActions.navigateToAuth
        )

        exerciseGraph(navController = navController)


        composable(NityaHealthDestinations.PROFILE_ROUTE) {
            ProfileScreen(
                isAuth = auth.isAuth,
                navigateUp = ::navigateUp,
                navigateToSignIn = navigationActions.navigateToAuth
            )
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

    LaunchedEffect(auth.isAuth, auth.isOnboard) {
        val TAG = "Root Nav Launched Effect Block"
        Log.d(TAG, "Launched")
        if (!auth.isOnboard) {
            navigationActions.navigateToOnboarding()
            Log.d(TAG, "Not Onboard Block")

        } else if (!auth.isAuth) {
            navigationActions.navigateToAuthAndClearBackStack()
            Log.d(TAG, "Not Auth Block")

        } else {
            navigationActions.navigateToDashboardAndClearBackStack()
            Log.d(TAG, "Else Block")
        }
        closeSplashScreen()
    }
}


private fun NavGraphBuilder.introScreen() {
    composable(
        route = NityaHealthDestinations.INTRO_ROUTE,
        enterTransition = { myFadeEnterTransition() },
        exitTransition = { myFadeExitTransition() },
        popEnterTransition = { myFadeEnterTransition() },
        popExitTransition = { myFadeExitTransition() }
    ) {
        IntroScreen()
    }
}

private fun NavGraphBuilder.dashboard(
    drawerState: DrawerState,
    userName: String?,
    navigationActions: NityaHealthNavigationActions,
    closeDrawer: () -> Unit,
    navigateTo: (String) -> Unit,
    openDrawer: () -> Unit
) {
    composable(route = NityaHealthDestinations.DASHBOARD_ROUTE) {
        MyDoubleBackPressForExitBackHandler()
        MyDrawer(
            drawerState = drawerState,
            userName = userName,
            closeDrawer = closeDrawer,
            navigateTo = navigateTo
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
}






