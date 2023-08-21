package com.kritan.nityahealth.feature_auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_auth.screens.OnBoardingScreen
import com.kritan.nityahealth.feature_auth.screens.SignInEmailScreen
import com.kritan.nityahealth.feature_auth.screens.SignInScreen
import com.kritan.nityahealth.feature_auth.screens.SignUpLocationScreen
import com.kritan.nityahealth.feature_auth.screens.SignUpScreen
import com.kritan.nityahealth.feature_auth.screens.SignUpVerifyScreen
import com.kritan.nityahealth.feature_auth.screens.WelcomeScreen
import com.kritan.nityahealth.ui.NityaHealthNavigationActions

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    val navigationActions = AuthNavigationActions(navController)
    val navigateToDashboard = NityaHealthNavigationActions(navController).navigateToDashboard

    navigation(startDestination = AuthDestinations.SIGN_IN_ROUTE, route = "auth") {

        composable(AuthDestinations.INTRO_ROUTE) {
            WelcomeScreen {

            }
        }

        composable(AuthDestinations.ONBOARDING_ROUTE) {
            OnBoardingScreen {

            }
        }


        composable(AuthDestinations.SIGN_IN_ROUTE) {
            SignInScreen(onSignInEmail = navigationActions.navigateToSignInEmail)
        }

        composable(AuthDestinations.SIGN_IN_EMAIL_ROUTE) {
            SignInEmailScreen(
                onNavigateUp = navigationActions.navigateUp,
                onSignInClick = navigateToDashboard,
                onNavigateToSignUp = navigationActions.navigateToSignUp
            )
        }

        composable(AuthDestinations.SIGN_UP_ROUTE) {
            SignUpScreen(
                onNavigateUp = navigationActions.navigateUp,
                onNavigateToSignIn = navigationActions.navigateToSignIn,
                onSignUpClick = navigationActions.navigateToSignUpVerify
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