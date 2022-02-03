package com.example.settings

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.settings.destinations.SettingsScreenLevel2Destination
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class,
    ExperimentalFoundationApi::class
)
@Destination(start = true)
@Composable
fun SettingsScreen(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Settings")
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Button(
            onClick = {
                navigator.navigate(SettingsScreenLevel2Destination)
            }
        ) {
            Text(text = "Level2")
        }
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

@Destination
@Composable
fun SettingsScreenLevel2(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Settings level 2")
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