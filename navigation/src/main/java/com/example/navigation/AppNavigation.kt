package com.example.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
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
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.Direction
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
    val showOnboarding = false//listOf(true, false).random()
    val navController = rememberAnimatedNavController()
    val engine = rememberAnimatedNavHostEngine(
        rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING
    )

    Scaffold (
        topBar = { TopBar() },
        bottomBar = {
            BottomNavigation(
                preSelectedItem = Home,
                navigationItems = BottomNavItems,
                onClick = { destinationScreen ->
                    navController.apply {
                        popBackStack()
                        navigateTo(destinationScreen.direction())
                    }
                }
            )
        }
    ) {
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
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "ComposeNavigation", fontSize = 18.sp) },
    )
}

private fun BottomNavItem.direction(): Direction =
    when (this) {
        is Home -> AppNavGraphs.home
        is Settings -> AppNavGraphs.settings
        else -> throw IllegalStateException("Unknown direction")
    }

val BottomNavItems = listOf(Home, Settings)

object Settings : BottomNavItem(
    title = "Settings",
    icon = Icons.Outlined.Settings,
    iconSelected = Icons.Default.Settings,
    route = AppNavGraphs.settings.route
)

object Home : BottomNavItem(
    title = "Home",
    icon = Icons.Outlined.Home,
    iconSelected = Icons.Default.Home,
    route = AppNavGraphs.home.route
)

open class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val route: String,
)

@Composable
fun BottomNavigation(
    preSelectedItem: BottomNavItem,
    navigationItems: List<BottomNavItem>,
    onClick: (BottomNavItem) -> Unit,
) {
    var selectedItem by remember { mutableStateOf("") }
    selectedItem = preSelectedItem.route

    BottomNavigation(
        modifier = Modifier,
    ) {
        navigationItems.forEach { item ->
            val itemIsSelected = selectedItem == item.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = rememberVectorPainter(
                            if (itemIsSelected) item.iconSelected
                            else item.icon
                        ),
                        contentDescription = item.title,
                    )
                },
                label = { Text(item.title) },
                selected = itemIsSelected,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.LightGray,
                onClick = {
                    selectedItem = item.route
                    onClick(item)
                },
            )
        }
    }
}
