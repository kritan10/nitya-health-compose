package com.kritan.nityahealth.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Top level navigation destinations and navigation actions.
 *
 * Nested route destinations and actions can be found in their corresponding features.
 */


/**
 * All routes of the app are defined in NityaHealthDestinations object.
 */
object NityaHealthDestinations {
    const val AUTH_ROUTE = "auth"

    //REQUIRED IN DRAWER
    const val DASHBOARD_ROUTE = "dashboard"
    const val PROFILE_ROUTE = "profile"
    const val APPOINTMENT_ROUTE = "appointment"
    const val DOCTORS_ROUTE = "doctors"
    const val FITNESS_ROUTE = "fitness"
    const val FOOD_ROUTE = "food"
    const val YOGA_ROUTE = "yoga"


    //REQUIRED IN DASHBOARD
    const val WELLNESS_ROUTE = "wellness"
    const val CONSULTANTS_ROUTE = "consultants"
    const val HEALTH_TOPICS_ROUTE = "health_topics"
    const val NEWS_ARTICLES_ROUTE = "news_articles"
    const val ACTIVITIES_ROUTE = "activities"
//    const val PROFILE_ROUTE = "profile"

    const val SETTINGS_ROUTE = "settings"
}


/**
 * The possible routing actions that can be executed in the application.
 *
 * The methods are defined as (NityaHealthDestination)->Unit
 *
 * @param navController The top level NavHostController of the app]
 *
 */
class NityaHealthNavigationActions(navController: NavHostController) {
    val navigateUp: () -> Unit = { navController.navigateUp() }

    val navigateToDashboard: () -> Unit = {
        navController.navigate(NityaHealthDestinations.DASHBOARD_ROUTE) {
            // Pop up to the start destination of the graph to avoid building up a large
            // stack of destinations on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when re-selecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    val navigateToAuth: () -> Unit = {
        navController.navigate(NityaHealthDestinations.AUTH_ROUTE) {
            launchSingleTop = true
        }
    }

    val navigateToProfile: () -> Unit = {
        navController.navigate(NityaHealthDestinations.PROFILE_ROUTE) {
            launchSingleTop = true
        }
    }

    val navigateToAppointment: () -> Unit = {
        navController.navigate(NityaHealthDestinations.APPOINTMENT_ROUTE)
    }

    val navigateToDoctors: () -> Unit = {
        navController.navigate(NityaHealthDestinations.DOCTORS_ROUTE)
    }

    val navigateToFitness: () -> Unit = {
        navController.navigate(NityaHealthDestinations.FITNESS_ROUTE)
    }

    val navigateToFood: () -> Unit = {
        navController.navigate(NityaHealthDestinations.FOOD_ROUTE)
    }

    val navigateToYoga: () -> Unit = {
        navController.navigate(NityaHealthDestinations.YOGA_ROUTE)
    }

    val navigateToWellness: () -> Unit = {
        navController.navigate(NityaHealthDestinations.WELLNESS_ROUTE)
    }

    val navigateToConsultants: () -> Unit = {
        navController.navigate(NityaHealthDestinations.CONSULTANTS_ROUTE)
    }

    val navigateToHealthTopics: () -> Unit = {
        navController.navigate(NityaHealthDestinations.HEALTH_TOPICS_ROUTE)
    }

    val navigateToNewsArticles: () -> Unit = {
        navController.navigate(NityaHealthDestinations.NEWS_ARTICLES_ROUTE)
    }

    val navigateToActivities: () -> Unit = {
        navController.navigate(NityaHealthDestinations.ACTIVITIES_ROUTE)
    }
}
