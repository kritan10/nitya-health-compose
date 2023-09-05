package com.kritan.nityahealth.ui.theme

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.myEnterTransition(): EnterTransition {
    return slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left)
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.myExitTransition(): ExitTransition {
    return slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left)
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.myPopEnterTransition(): EnterTransition {
    return slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right)
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.myPopExitTransition(): ExitTransition {
    return slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right)
}

fun myFadeEnterTransition(): EnterTransition {
    return fadeIn(TweenSpec(500))
}

fun myFadeExitTransition(): ExitTransition {
    return fadeOut(TweenSpec(500))
}

fun mySlideUpTransition(): EnterTransition{
    return slideInVertically()
}

