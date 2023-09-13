package com.kritan.nityahealth.auth.presentation.screens.boarding

import androidx.annotation.DrawableRes
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.kritan.nityahealth.R
import com.kritan.nityahealth.ui.components.MyTextButton
import kotlinx.coroutines.launch

sealed class OnboardingItem(@DrawableRes val image: Int, val label: String) {
    object OnboardingFirst :
        OnboardingItem(R.drawable.onboarding_image_1, "Talk to our\nDoctors")

    object OnboardingSecond :
        OnboardingItem(R.drawable.onboarding_image_2, "Get fitness class\nfrom Professional")

    object OnboardingThird :
        OnboardingItem(R.drawable.onboarding_image_3, "Start Yoga class\nwith Medilab")

    object OnboardingFourth :
        OnboardingItem(R.drawable.onboarding_image_4, "Get food Plan\nFrom Experts")
}

private val onBoardingItemsList = listOf(
    OnboardingItem.OnboardingFirst,
    OnboardingItem.OnboardingSecond,
    OnboardingItem.OnboardingThird,
    OnboardingItem.OnboardingFourth
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navigateToSignIn: () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {

        Box {
            val pageCount = onBoardingItemsList.size
            val scope = rememberCoroutineScope()
            val pagerState = rememberPagerState { pageCount }

            HorizontalPager(state = pagerState) {
                OnBoardingLayout(onboardingItem = onBoardingItemsList[it])
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
                    onClick = { navigateToSignIn() })
                Row {
                    repeat(onBoardingItemsList.size) {
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
                        navigateToSignIn()
                    }
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