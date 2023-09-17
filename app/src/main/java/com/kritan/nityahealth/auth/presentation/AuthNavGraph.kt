package com.kritan.nityahealth.auth.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.auth.presentation.screens.post_signup.SignUpLocationScreen
import com.kritan.nityahealth.auth.presentation.screens.post_signup.SignUpVerifyScreen
import com.kritan.nityahealth.auth.presentation.screens.signin.SignInScreen
import com.kritan.nityahealth.auth.presentation.screens.signin_email.SignInEmailScreen
import com.kritan.nityahealth.auth.presentation.screens.signup_email.SignUpScreen
import com.kritan.nityahealth.ui.NityaHealthDestinations
import com.kritan.nityahealth.ui.components.MyDoubleBackPressForExitBackHandler
import com.kritan.nityahealth.ui.theme.myFadeExitTransition
import com.kritan.nityahealth.ui.theme.myPopExitTransition

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    navigateToDashboardAndClearBackStack: () -> Unit,
) {
    val navigationActions = AuthNavigationActions(navController)

    navigation(
        route = NityaHealthDestinations.AUTH_ROUTE,
        startDestination = AuthDestinations.SIGN_IN_ROUTE
    ) {

        composable(AuthDestinations.SIGN_IN_ROUTE) {
            MyDoubleBackPressForExitBackHandler()
            SignInScreen(
                onSignInEmail = navigationActions.navigateToSignInEmail,
                onSkipSignIn = navigateToDashboardAndClearBackStack
            )
        }

        composable(
            route = AuthDestinations.SIGN_IN_EMAIL_ROUTE,
            exitTransition = { myFadeExitTransition() },
            popExitTransition = { myPopExitTransition() }
        ) {
            SignInEmailScreen(
                navigateUp = navigationActions.navigateUp,
                navigateToSignUp = navigationActions.navigateToSignUp,
            )
        }

        composable(
            route = AuthDestinations.SIGN_UP_ROUTE,
            exitTransition = { myFadeExitTransition() },
            popExitTransition = { myPopExitTransition() }
        ) {
            SignUpScreen(
                onNavigateUp = navigationActions.navigateUp,
                onNavigateToSignIn = navigationActions.navigateToSignInEmailAndClearBackStack,
            )
        }

        composable(AuthDestinations.SIGN_UP_VERIFY_ROUTE) {
            SignUpVerifyScreen(
                onNavigateUp = navigationActions.navigateUp,
                navigateToLocationPicker = navigationActions.navigateToSignUpLocation
            )
        }

        composable(AuthDestinations.SIGN_UP_LOCATION_ROUTE) {
            SignUpLocationScreen(onNavigateUp = navigationActions.navigateUp)
        }
    }
}