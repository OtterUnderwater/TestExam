package com.example.testexam.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testexam.view.screens.Splash
import com.example.testexam.view.screens.auth.AuthView

@Composable
fun Navigation(controller: NavHostController){
    NavHost(
        navController = controller,
        startDestination = RoutesNav.SPLASH
    ){
        composable(RoutesNav.SPLASH) {
            Splash(controller)
        }

        composable(RoutesNav.AUTH) {
            AuthView(controller)
        }
    }
}