package com.kritan.nityahealth.feature_wellness.presentation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kritan.nityahealth.ui.constants.MyPadding
import com.kritan.nityahealth.ui.layouts.MyGridLayout
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout
import com.kritan.nityahealth.ui.theme.mRoundedCorner


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WellnessScreen(
    initialPage: Int = 0,
    navigateUp: () -> Unit,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    val wellnessTabItemsList = wellnessViewModel.wellnessTabItemsList

    var currentTab by remember {
        mutableIntStateOf(initialPage)
    }

    val tabRowState = rememberLazyListState()
    val pagerState = rememberPagerState(
        initialPage = initialPage,
        pageCount = { wellnessTabItemsList.size }
    )

    fun onTabChange(newTabIndex: Int) {
        currentTab = newTabIndex
    }

    LaunchedEffect(pagerState.currentPage) {
        tabRowState.animateScrollToItem(pagerState.currentPage)
        currentTab = pagerState.currentPage
    }

    LaunchedEffect(currentTab) {
        pagerState.animateScrollToPage(currentTab)
    }

    MyScaffoldLayout(
        title = "Wellness",
        navigateUp = navigateUp,
        padding = MyPadding.TopOnly
    ) {
        Column {
            LazyRow(
                state = tabRowState,
                modifier = Modifier
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // initial empty spacing
                item {}

                items(wellnessTabItemsList) {
                    TabRowItem(
                        tab = it,
                        currentTabIndex = currentTab,
                        onTabChange = ::onTabChange
                    )
                }

                // final empty spacing
                item {}
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
private fun TabRowItem(
    tab: WellnessTab,
    currentTabIndex: Int,
    onTabChange: (Int) -> Unit
) {
    val isActive = tab.position == currentTabIndex
    var modifier = Modifier
        .border(1.dp, MaterialTheme.colorScheme.primary, mRoundedCorner)
        .clickable {
            onTabChange(tab.position)
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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            item.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyGridLayout()
    }
}