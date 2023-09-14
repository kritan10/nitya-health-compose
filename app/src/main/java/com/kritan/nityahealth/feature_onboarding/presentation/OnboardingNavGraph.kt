package com.kritan.nityahealth.feature_onboarding.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_onboarding.presentation.screens.OnboardingBoardScreen
import com.kritan.nityahealth.feature_onboarding.presentation.screens.OnboardingIntroScreen
import com.kritan.nityahealth.ui.NityaHealthDestinations

object OnboardDestinations {
    const val INTRO_ROUTE = "intro"
    const val ONBOARD_ROUTE = "boarding"
}

fun NavGraphBuilder.onboardingGraph(
    navController: NavHostController,
    navigateToAuthAndClearBackStack: () -> Unit
) {
    navigation(
        route = NityaHealthDestinations.ONBOARDING_ROUTE,
        startDestination = OnboardDestinations.INTRO_ROUTE
    ) {
        composable(OnboardDestinations.INTRO_ROUTE) {
            val navigateToOnboardingBoard: () -> Unit =
                { navController.navigate(OnboardDestinations.ONBOARD_ROUTE) }
            OnboardingIntroScreen(navigateToOnboardingBoard = navigateToOnboardingBoard)
        }

        composable(OnboardDestinations.ONBOARD_ROUTE) {
            OnboardingBoardScreen(onBoardingComplete = navigateToAuthAndClearBackStack)
        }

    }
}