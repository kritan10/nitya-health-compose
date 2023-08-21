package com.kritan.nityahealth.feature_wellness.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kritan.nityahealth.commons.components.MyItemCard
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.feature_dashboard.dashboardItems
import com.kritan.nityahealth.ui.theme.mRoundedCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessScreen(
    openDrawer: () -> Unit,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    val currentTab = wellnessViewModel.currentTab
    val lazyListState = rememberLazyListState()

    LaunchedEffect(true) {
        wellnessViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ScrollToItem -> lazyListState.animateScrollToItem(event.index, -160)
            }
        }
    }

    Scaffold(topBar = {
        MyTopAppBar(title = "Wellness", openDrawer)
    }) { pv ->
        Column(Modifier.padding(pv)) {
            LazyRow(
                state = lazyListState,
                modifier = Modifier
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Spacer(modifier = Modifier.width(10.dp))
                    RowItem(
                        WellnessTab.Fitness,
                        currentTab,
                        wellnessViewModel::onTabClick
                    )
                }
                item {
                    RowItem(
                        WellnessTab.Food,
                        currentTab,
                        wellnessViewModel::onTabClick
                    )
                }
                item {
                    RowItem(
                        WellnessTab.PersonalCare,
                        currentTab,
                        wellnessViewModel::onTabClick
                    )
                }
                item {
                    RowItem(
                        WellnessTab.HealthTopics,
                        currentTab,
                        wellnessViewModel::onTabClick
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(dashboardItems) { item ->
                    MyItemCard(item.title, item.image) { }
                }
            }
        }
    }
}


@Composable
private fun RowItem(
    tab: WellnessTab,
    currentTab: WellnessTab,
    onTabClick: (WellnessTab) -> Unit
) {


    val isActive = tab == currentTab
    val modifier = if (!isActive) {
        Modifier
            .clickable {
                onTabClick(tab)
            }
            .border(1.dp, MaterialTheme.colorScheme.primary, mRoundedCorner)
            .padding(vertical = 10.dp, horizontal = 30.dp)
    } else {
        Modifier
            .clickable {
                onTabClick(tab)
            }
            .border(1.dp, MaterialTheme.colorScheme.primary, mRoundedCorner)
            .background(MaterialTheme.colorScheme.primary, mRoundedCorner)
            .padding(vertical = 10.dp, horizontal = 30.dp)
    }

    Text(
        tab.title,
        modifier,
        color = if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
    )
}