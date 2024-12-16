package com.example.testexam.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.testexam.view.navigation.RoutesNav
import kotlinx.coroutines.delay

@Composable
fun Splash (controller: NavHostController){

    LaunchedEffect(Unit) {
        delay(2000L)
        controller.navigate(RoutesNav.AUTH){
            popUpTo(RoutesNav.SPLASH){
                inclusive = true
            }
        }
    }

    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Splash")
    }

}