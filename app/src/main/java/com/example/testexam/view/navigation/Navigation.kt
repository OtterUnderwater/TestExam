package com.example.testexam.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.tooling.data.R
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testexam.view.screens.Splash
import com.example.testexam.view.screens.auth.AuthView
import com.example.testexam.view.screens.favorite.FavoriteView
import com.example.testexam.view.screens.home.HomeView
import com.example.testexam.view.screens.meme.MemeView

@Composable
fun Navigation(controller: NavHostController, isVisibleBar: MutableState<Boolean>){
    NavHost(
        navController = controller,
        startDestination = RoutesNav.SPLASH
    ){
        composable(RoutesNav.SPLASH) {
            isVisibleBar.value = false
            Splash(controller)
        }
        composable(RoutesNav.AUTH) {
            isVisibleBar.value = false
            AuthView(controller)
        }
        composable(RoutesNav.HOME) {
            isVisibleBar.value = true
            HomeView(controller)
        }
        composable(RoutesNav.FAVORITE) {
            isVisibleBar.value = true
            FavoriteView(controller)
        }
        composable(RoutesNav.MEME) {
            isVisibleBar.value = true
            MemeView(controller)
        }
    }
}