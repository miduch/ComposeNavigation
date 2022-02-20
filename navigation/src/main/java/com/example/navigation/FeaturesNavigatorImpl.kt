package com.example.navigation

import com.example.homescreen.destinations.HomeScreenDestination
import com.example.onboarding.destinations.OnBoardingScreenDestination
import com.example.settings.destinations.SettingsScreenDestination
import com.example.shared.FeaturesNavigator
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

class FeaturesNavigatorImpl(
    private val navigator: DestinationsNavigator
): FeaturesNavigator {

    override fun launchOnboarding() {
        navigator.navigate(OnBoardingScreenDestination)
    }

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
