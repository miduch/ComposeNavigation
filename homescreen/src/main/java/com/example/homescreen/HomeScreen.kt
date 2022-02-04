package com.example.homescreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.homescreen.destinations.HomeScreenLevel2Destination
import com.example.shared.FeaturesNavigator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    featuresNavigator: FeaturesNavigator,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Homescreen")
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Button(
            onClick = {
                navigator.navigate(HomeScreenLevel2Destination)
            }
        ) {
            Text(text = "Level2")
        }
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Button(
            onClick = {
                featuresNavigator.openSettings()
            }
        ) {
            Text(text = "Settings")
        }
    }
}

@Destination
@Composable
fun HomeScreenLevel2(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Homescreen level 2")
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Button(
            onClick = {
                navigator.popBackStack()
            }
        ) {
            Text(text = "Back")
        }
    }
}