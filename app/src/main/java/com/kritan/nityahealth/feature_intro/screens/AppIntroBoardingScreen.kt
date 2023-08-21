package com.kritan.nityahealth.feature_intro.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.commons.components.MyTextButton
import com.kritan.nityahealth.feature_intro.utils.OnboardingItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navigateToLogin: () -> Unit
) {
    Box {
        val onboardingList = listOf(
            OnboardingItem.OnboardingFirst,
            OnboardingItem.OnboardingSecond,
            OnboardingItem.OnboardingThird,
            OnboardingItem.OnboardingFourth
        )
        val pageCount = onboardingList.size
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState { pageCount }

        HorizontalPager(state = pagerState) {
            OnBoardingLayout(onboardingItem = onboardingList[it])
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 50.dp, end = 24.dp, start = 24.dp)
        ) {
            MyTextButton(
                label = "Skip",
                onClick = { navigateToLogin() })
            Row {
                repeat(onboardingList.size) {
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
            MyTextButton(label = "Next") {
                if (pagerState.currentPage < pageCount - 1) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    navigateToLogin()
                }
            }

        }
    }
}

@Composable
fun OnBoardingLayout(
    onboardingItem: OnboardingItem
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(bottom = 100.dp)
    ) {
        Image(
            painter = painterResource(onboardingItem.image),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(onboardingItem.label, textAlign = TextAlign.Center)
    }
}