package com.example.onboarding

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
import com.example.shared.FeaturesNavigator
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Destination
@Composable
fun OnBoardingScreen(
    navigator: DestinationsNavigator,
    featuresNavigator: FeaturesNavigator,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Onboarding")
        Spacer(modifier = Modifier.padding(vertical = 12.dp))
        Button(
            onClick = {
                featuresNavigator.closeOnboarding()
            }
        ) {
            Text(text = "Done")
        }
    }
}