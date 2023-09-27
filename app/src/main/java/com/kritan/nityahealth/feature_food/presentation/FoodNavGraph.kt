package com.kritan.nityahealth.feature_food.presentation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kritan.nityahealth.feature_food.presentation.screens.FoodGoalScreen
import com.kritan.nityahealth.feature_food.presentation.screens.FoodHomeScreen
import com.kritan.nityahealth.feature_food.presentation.screens.FoodUserDetailScreen
import com.kritan.nityahealth.feature_food.presentation.screens.FoodViewModel
import com.kritan.nityahealth.ui.NityaHealthDestinations

fun NavGraphBuilder.foodGraph(
    navController: NavHostController,
    navigateToDashboard: () -> Unit
) {
    navigation(
        route = NityaHealthDestinations.FOOD_ROUTE,
        startDestination = FoodDestinations.FOOD_HOME_ROUTE
    ) {
        val navigationActions = FoodNavigationActions(navController)

        composable(FoodDestinations.FOOD_HOME_ROUTE) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NityaHealthDestinations.FOOD_ROUTE)
            }

            val viewModel: FoodViewModel = hiltViewModel(parentEntry)

            LaunchedEffect(Unit) {
                if (viewModel.uiState.foodUserDetail == null) {
                    viewModel.initializeFoodUserDetail()
                    navigationActions.navigateToWeightGoal()
                }
            }

            FoodHomeScreen(viewModel = viewModel, navigateToDashboard = navigateToDashboard)
        }

        composable(FoodDestinations.WEIGHT_GOAL_ROUTE) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NityaHealthDestinations.FOOD_ROUTE)
            }

            val viewModel: FoodViewModel = hiltViewModel(parentEntry)

            FoodGoalScreen(
                viewModel = viewModel,
                navigateUp = navigateToDashboard,
                navigateToFoodUserDetail = navigationActions.navigateToFoodUserDetails
            )
        }

        composable(FoodDestinations.USER_FOOD_DETAILS_ROUTE) {
            val parentEntry = remember(it) {
                navController.getBackStackEntry(NityaHealthDestinations.FOOD_ROUTE)
            }

            val viewModel: FoodViewModel = hiltViewModel(parentEntry)

            FoodUserDetailScreen(
                viewModel = viewModel,
                navigateUp = navigationActions.navigateUp,
                navigateToFoodHome = navigationActions.navigateToFoodHome
            )

        }

    }
}