package com.kritan.nityahealth.feature_dashboard.utils

import androidx.annotation.DrawableRes
import com.kritan.nityahealth.R

data class DashboardItem(val title: String, @DrawableRes val image: Int)

val dashboardItems = listOf<DashboardItem>(
    DashboardItem("Wellness", R.drawable.ic_dashboard_wellness),
    DashboardItem("Consultants", R.drawable.ic_dashboard_consultants),
    DashboardItem("Health Topics", R.drawable.ic_dashboard_topics),
    DashboardItem("News/Articles", R.drawable.ic_dashboard_news),
    DashboardItem("Activities", R.drawable.ic_dashboard_activities),
    DashboardItem("Profile", R.drawable.ic_dashboard_profile),
)