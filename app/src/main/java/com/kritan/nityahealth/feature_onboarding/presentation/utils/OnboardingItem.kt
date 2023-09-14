package com.kritan.nityahealth.feature_onboarding.presentation.utils

import androidx.annotation.DrawableRes
import com.kritan.nityahealth.R

sealed class OnboardingItem(@DrawableRes val image: Int, val label: String) {
    object OnboardingFirst :
        OnboardingItem(R.drawable.onboarding_image_1, "Talk to our\nDoctors")

    object OnboardingSecond :
        OnboardingItem(R.drawable.onboarding_image_2, "Get fitness class\nfrom Professional")

    object OnboardingThird :
        OnboardingItem(R.drawable.onboarding_image_3, "Start Yoga class\nwith Medilab")

    object OnboardingFourth :
        OnboardingItem(R.drawable.onboarding_image_4, "Get food Plan\nFrom Experts")

    companion object {
        val onBoardingItemsList = listOf(
            OnboardingFirst,
            OnboardingSecond,
            OnboardingThird,
            OnboardingFourth
        )
    }
}