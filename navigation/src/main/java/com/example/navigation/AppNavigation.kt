package com.example.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.homescreen.destinations.HomeScreenDestination
import com.example.homescreen.destinations.HomeScreenLevel2Destination
import com.example.onboarding.destinations.OnBoardingScreenDestination
import com.example.settings.destinations.SettingsScreenDestination
import com.example.settings.destinations.SettingsScreenLevel2Destination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.spec.NavGraphSpec

object AppNavGraphs {
    val onboarding = object : NavGraphSpec {
        override val route = "onboarding_route"
        override val destinationsByRoute = listOf(OnBoardingScreenDestination).associateBy { it.route }
        override val startRoute = OnBoardingScreenDestination
    }

    val settings = object : NavGraphSpec {
        override val route = "settings_route"
        override val destinationsByRoute = listOf(
            SettingsScreenDestination,
            SettingsScreenLevel2Destination
        ).associateBy { it.route }
        override val startRoute = SettingsScreenDestination
    }

    val home = object : NavGraphSpec {
        override val route = "homescreen_route"
        override val destinationsByRoute = listOf(
            HomeScreenDestination,
            HomeScreenLevel2Destination,
        ).associateBy { it.route }
        override val startRoute = HomeScreenDestination
        override val nestedNavGraphs = listOf(onboarding, settings)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    DestinationsNavHost(navGraph = AppNavGraphs.home)
}
