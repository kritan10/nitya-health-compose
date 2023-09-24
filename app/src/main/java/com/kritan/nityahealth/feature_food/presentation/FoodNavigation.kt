package com.kritan.nityahealth.feature_food.presentation

import androidx.navigation.NavHostController

object FoodDestinations {
    const val WEIGHT_GOAL_ROUTE = "weight_goal"
    const val USER_FOOD_DETAILS_ROUTE = "food_user"
    const val FOOD_HOME_ROUTE = "food_home"
}

class FoodNavigationActions(navController: NavHostController) {

    val navigateUp: () -> Unit = { navController.navigateUp() }

    val navigateToWeightGoal: () -> Unit =
        { navController.navigate(FoodDestinations.WEIGHT_GOAL_ROUTE) }

    val navigateToFoodUserDetails: () -> Unit =
        { navController.navigate(FoodDestinations.USER_FOOD_DETAILS_ROUTE) }

    val navigateToFoodHome: () -> Unit =
        { navController.navigate(FoodDestinations.FOOD_HOME_ROUTE) }

}