package com.kritan.nityahealth.feature_dashboard

import androidx.annotation.DrawableRes
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.NityaHealthDestinations

data class DashboardItem(val title: String, @DrawableRes val image: Int, val route: String)

val dashboardItems = listOf(
    DashboardItem(
        "Wellness",
        R.drawable.ic_dashboard_wellness,
        NityaHealthDestinations.WELLNESS_ROUTE
    ),
    DashboardItem(
        "Consultants",
        R.drawable.ic_dashboard_consultants,
        NityaHealthDestinations.CONSULTANTS_ROUTE
    ),
    DashboardItem(
        "Health Topics",
        R.drawable.ic_dashboard_topics,
        NityaHealthDestinations.HEALTH_TOPICS_ROUTE
    ),
    DashboardItem(
        "News/Articles",
        R.drawable.ic_dashboard_news,
        NityaHealthDestinations.NEWS_ARTICLES_ROUTE
    ),
    DashboardItem(
        "Activities",
        R.drawable.ic_dashboard_activities,
        NityaHealthDestinations.ACTIVITIES_ROUTE
    ),
    DashboardItem(
        "Profile",
        R.drawable.ic_dashboard_profile,
        NityaHealthDestinations.PROFILE_ROUTE
    ),
)

