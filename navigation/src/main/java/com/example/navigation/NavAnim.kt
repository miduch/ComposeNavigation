package com.example.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations


@OptIn(ExperimentalAnimationApi::class)
val FADING by lazy {
    RootNavGraphDefaultAnimations(
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
    )
}

@OptIn(ExperimentalAnimationApi::class)
val SLIDE_IN_VERTICALLY by lazy {
    NestedNavGraphDefaultAnimations(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Up
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Down
            )
        },
    )
}