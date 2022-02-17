package com.example.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.homescreen.HomeScreen
import com.example.homescreen.destinations.HomeScreenDestination
import com.example.homescreen.destinations.HomeScreenLevel2Destination
import com.example.onboarding.OnBoardingScreen
import com.example.onboarding.destinations.OnBoardingScreenDestination
import com.example.settings.destinations.SettingsScreenDestination
import com.example.settings.destinations.SettingsScreenLevel2Destination
import com.example.shared.FeaturesNavigator
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.manualcomposablecalls.DestinationScope
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.NavGraphSpec

object AppNavGraphs {
    val onboarding = object : NavGraphSpec {
        override val route = "onboarding_route"
        override val destinationsByRoute =
            listOf(OnBoardingScreenDestination).associateBy { it.route }
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

class FeaturesNavigatorImpl(private val navigator: DestinationsNavigator): FeaturesNavigator {

    override fun closeOnboarding() {
        navigator.navigate(HomeScreenDestination) {
            this.popUpTo(OnBoardingScreenDestination.route) {
                inclusive = true
            }
        }
    }

    override fun openSettings() {
        navigator.navigate(SettingsScreenDestination)
    }
}

fun DestinationScope<*>.featuresNavigator() = FeaturesNavigatorImpl(destinationsNavigator)


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun AppNavigation() {
    val showOnboarding = listOf(true, false).random()
    val engine = rememberAnimatedNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING
    )
    val navController = rememberAnimatedNavController()
    DestinationsNavHost(
        navGraph = AppNavGraphs.home,
        engine = engine,
        navController = navController,
        startRoute = if (showOnboarding) AppNavGraphs.onboarding else AppNavGraphs.home.startRoute
    ) {
        composable(HomeScreenDestination) {
            HomeScreen(
                navigator = destinationsNavigator,
                featuresNavigator = featuresNavigator()
            )
        }
        composable(OnBoardingScreenDestination) {
            OnBoardingScreen(
                navigator = destinationsNavigator,
                featuresNavigator = featuresNavigator()
            )
        }
    }
}
