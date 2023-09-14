package com.kritan.nityahealth.feature_onboarding.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.feature_onboarding.presentation.utils.OnBoardingLayout
import com.kritan.nityahealth.feature_onboarding.presentation.utils.OnboardingItem
import com.kritan.nityahealth.ui.components.MyTextButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingBoardScreen(
    onBoardingComplete: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        val onBoardingItemsList = OnboardingItem.onBoardingItemsList

        Box {
            val pageCount = onBoardingItemsList.size
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState { pageCount }

            HorizontalPager(state = pagerState) { page ->
                OnBoardingLayout(onboardingItem = onBoardingItemsList[page])
            }

            OnboardingControlsRow(
                scope = scope,
                pagerState = pagerState,
                onBoardingComplete = onBoardingComplete,
                onBoardingItemsList = onBoardingItemsList
            )

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BoxScope.OnboardingControlsRow(
    pagerState: PagerState,
    scope: CoroutineScope,
    onBoardingItemsList: List<OnboardingItem>,
    onBoardingComplete: () -> Unit,
) {
    val pageCount = onBoardingItemsList.size
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .fillMaxWidth()
            .padding(bottom = 50.dp, end = 24.dp, start = 24.dp)
    ) {
        val isNotLastPage = pagerState.currentPage < pageCount - 1

        // Skip To Login
        MyTextButton(
            label = "Skip",
            onClick = onBoardingComplete,
            modifier = if (!isNotLastPage) Modifier.alpha(0F) else Modifier
        )

        // Circular Indicators
        Row {
            repeat(pageCount) {
                if (it != 0) Spacer(modifier = Modifier.width(15.dp))
                Box(
                    Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(
                            if (it == pagerState.currentPage) Color.DarkGray else Color.White
                        )
                )
            }
        }

        // Next Onboard Item
        MyTextButton(label = if (isNotLastPage) "Next" else "Login") {
            if (isNotLastPage) {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            } else {
                onBoardingComplete()
            }
        }
    }
}