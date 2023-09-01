package com.kritan.nityahealth.feature_wellness.presentation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kritan.nityahealth.commons.components.MyTopAppBar
import com.kritan.nityahealth.ui.layouts.MyGridLayout
import com.kritan.nityahealth.ui.theme.mRoundedCorner
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun WellnessScreen(
    initialPage: Int = 0,
    navigateUp: () -> Unit,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    val wellnessTabItemsList = wellnessViewModel.wellnessTabItemsList
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { wellnessTabItemsList.size }
    )

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            wellnessViewModel.uiEvent.collect { event ->
                when (event) {
                    is UiEvent.ScrollTo -> {
                        lazyListState.animateScrollToItem(
                            event.index,
                            if (event.index > lazyListState.firstVisibleItemIndex) 200 else -100
                        )
                        pagerState.scrollToPage(event.index)
                    }
                }
            }
        }
    }

    Scaffold(topBar = {
        MyTopAppBar(title = "Wellness", navigateUp)
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
                    Spacer(modifier = Modifier.width(1.dp))
                }
                items(wellnessTabItemsList) { it ->
                    RowItem(
                        tab = it,
                        tabIndex = it.position,
                        currentTabIndex = pagerState.currentPage,
                        onTabChange = wellnessViewModel::onTabChange
                    )
                }
                item {
                    Spacer(modifier = Modifier.width(1.dp))
                }
            }

            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(
                        Alignment.TopCenter
                    )
            ) {
                PagerItem(item = wellnessTabItemsList[it])
            }
        }
    }
}


@Composable
private fun RowItem(
    tab: WellnessTab,
    tabIndex: Int,
    currentTabIndex: Int,
    onTabChange: (Int) -> Unit
) {
    val isActive = tabIndex == currentTabIndex
    var modifier = Modifier
        .border(1.dp, MaterialTheme.colorScheme.primary, mRoundedCorner)
        .clickable {
            onTabChange(tabIndex)
            Log.d("Button", "Clicked")
        }
    if (isActive) {
        modifier = modifier.background(MaterialTheme.colorScheme.primary, mRoundedCorner)
    }
    modifier = modifier.padding(vertical = 10.dp, horizontal = 30.dp)
    Text(
        tab.title,
        modifier,
        color = if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onBackground
    )
}


@Composable
private fun PagerItem(item: WellnessTab) {
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
    ) {
        when (item) {
            WellnessTab.Fitness -> MyGridLayout()
            WellnessTab.Food -> MyGridLayout()
            WellnessTab.HealthTopics -> MyGridLayout()
            WellnessTab.PersonalCare -> MyGridLayout()
        }
    }
}