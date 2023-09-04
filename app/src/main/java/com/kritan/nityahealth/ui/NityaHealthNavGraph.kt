package com.kritan.nityahealth.ui

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kritan.nityahealth.auth.data.models.AuthState
import com.kritan.nityahealth.auth.presentation.AuthDestinations
import com.kritan.nityahealth.auth.presentation.authGraph
import com.kritan.nityahealth.ui.components.MyDrawer
import com.kritan.nityahealth.feature_consultants.presentation.ConsultantsScreen
import com.kritan.nityahealth.feature_dashboard.presentation.DashboardScreen
import com.kritan.nityahealth.feature_doctor.presentation.doctorsGraph
import com.kritan.nityahealth.feature_exercise.presentation.exerciseGraph
import com.kritan.nityahealth.feature_user.presentation.ProfileScreen
import com.kritan.nityahealth.feature_wellness.presentation.WellnessScreen
import com.kritan.nityahealth.ui.layouts.EmptyScreen
import com.kritan.nityahealth.ui.theme.myEnterTransition
import com.kritan.nityahealth.ui.theme.myExitTransition
import com.kritan.nityahealth.ui.theme.myFadeEnterTransition
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NityaHealthNavGraph(
    drawerState: DrawerState,
    authStateFlow: StateFlow<AuthState>,
    context: Context,
    openDrawer: () -> Unit,
    closeDrawer: () -> Unit,
) {
    val navController = rememberNavController()
    val navigationActions = NityaHealthNavigationActions(navController)
    val startDestination = NityaHealthDestinations.AUTH_ROUTE
    val auth by authStateFlow.collectAsState()


    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    LaunchedEffect(Unit) {
        if (auth.isAuth) {
            navigateTo(NityaHealthDestinations.DASHBOARD_ROUTE)
        } else if (auth.isOnboard) {
            navigateTo(AuthDestinations.SIGN_IN_ROUTE)
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { myEnterTransition() },
        exitTransition = { myExitTransition() },
        popEnterTransition = { myPopEnterTransition() },
        popExitTransition = { myPopExitTransition() },
    ) {

        authGraph(navController)

        doctorsGraph(navController)

        exerciseGraph(navController)

        composable(
            route = NityaHealthDestinations.DASHBOARD_ROUTE,
            enterTransition = { myFadeEnterTransition() },
            popEnterTransition = { myPopEnterTransition() },
        ) {
            BackHandler(true) {
                ActivityCompat.finishAffinity(context as Activity)
            }
            MyDrawer(
                drawerState,
                auth.userName,
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





