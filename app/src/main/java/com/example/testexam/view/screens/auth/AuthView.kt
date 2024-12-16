package com.example.testexam.view.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun AuthView (controller: NavHostController){
    val context = LocalContext.current
    val vm = viewModel{ AuthViewModel() }


}