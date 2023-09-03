package com.kritan.nityahealth.feature_dashboard.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.layouts.MyGridItem
import com.kritan.nityahealth.ui.layouts.MyGridLayout
import com.kritan.nityahealth.ui.theme.comfortaaFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    openDrawer: () -> Unit,
    navigateToWellness: () -> Unit,
    navigateToConsultants: () -> Unit,
    navigateToNewsArticles: () -> Unit,
    navigateToActivities: () -> Unit,
    navigateToProfile: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(Icons.Default.Menu, "Menu")
                    }
                },
                title = { Text("Dashboard") },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, "Menu")
                    }
                    IconButton(onClick = navigateToProfile) {
                        Icon(Icons.Default.AccountCircle, "Menu")
                    }
                },
            )
        },
    )
    { pv ->
        Column(
            modifier = Modifier.padding(pv)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 50.dp))
                    .background(
                        MaterialTheme.colorScheme.primary
                    )
                    .padding(20.dp)
            ) {
                Text(
                    text = "Hello ${viewModel.userName},\nWelcome to NityaHealth",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = comfortaaFontFamily,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 40.sp,
                    ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            val dashboardItems = listOf(
                MyGridItem(
                    "Wellness",
                    R.drawable.ic_dashboard_wellness,
                    navigateToWellness
                ),
                MyGridItem(
                    "Consultants",
                    R.drawable.ic_dashboard_consultants,
                    navigateToConsultants
                ),
                MyGridItem(
                    "Health Topics",
                    R.drawable.ic_dashboard_topics,
                    navigateToWellness
                ),
                MyGridItem(
                    "News/Articles",
                    R.drawable.ic_dashboard_news,
                    navigateToNewsArticles
                ),
                MyGridItem(
                    "Activities",
                    R.drawable.ic_dashboard_activities,
                    navigateToActivities
                ),
                MyGridItem(
                    "Profile",
                    R.drawable.ic_dashboard_profile,
                    navigateToProfile
                ),
            )
            MyGridLayout(dashboardItems)
        }
    }
}

