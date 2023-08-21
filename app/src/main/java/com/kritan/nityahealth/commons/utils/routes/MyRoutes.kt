package com.kritan.nityahealth.commons.utils.routes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_auth.screens.SignInEmailScreen
import com.kritan.nityahealth.feature_auth.screens.SignInScreen
import com.kritan.nityahealth.feature_auth.screens.SignUpLocationScreen
import com.kritan.nityahealth.feature_auth.screens.SignUpScreen
import com.kritan.nityahealth.feature_auth.screens.SignUpVerifyScreen

fun NavGraphBuilder.authGraph(navController: NavController) {
    fun navigateUp() = navController.navigateUp()
    fun navigateTo(route: Routes) = navController.navigate(route.route)

    navigation(startDestination = AuthRoutes.SignIn.route, route = "auth") {
        composable(AuthRoutes.SignIn.route) {
            SignInScreen(onSignInEmail = { navigateTo(AuthRoutes.SignInEmail) })
        }

        composable(AuthRoutes.SignInEmail.route) {
            SignInEmailScreen(
                onNavigateUp = { navigateUp() },
                onSignInClick = { navController.navigate("app") },
                onNavigateToSignUp = { navigateTo(AuthRoutes.SignUp) }
            )
        }

        composable(AuthRoutes.SignUp.route) {
            SignUpScreen(
                onNavigateUp = { navigateUp() },
                onNavigateToSignIn = { navigateTo(AuthRoutes.SignIn) },
                onSignUpClick = { navigateTo(AuthRoutes.SignUpOTP) }
            )
        }

        composable(AuthRoutes.SignUpOTP.route) {
            SignUpVerifyScreen(
                onNavigateUp = { navigateUp() },
                navigateToLocationPicker = { navigateTo(AuthRoutes.SignUpLocation) })
        }

        composable(AuthRoutes.SignUpLocation.route) {
            SignUpLocationScreen(onNavigateUp = { navigateUp() })
        }
    }
}

fun NavGraphBuilder.appGraph(navController: NavController) {

    fun navigateUp() = navController.navigateUp()
    fun navigateTo(route: Routes) = navController.navigate(route.route)

    navigation(startDestination = Routes.Dashboard.route, route = "app") {


    }
}