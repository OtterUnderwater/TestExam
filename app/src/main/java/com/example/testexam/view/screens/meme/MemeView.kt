package com.example.testexam.view.screens.meme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constans.currentMeme
import com.example.testexam.models.database.FavoriteMemes

@Composable
fun MemeView (controller: NavHostController){
    val vm = viewModel { MemeViewModel() }
    val state = vm.listFavorit
    var catFavorit by remember { mutableStateOf(FavoriteMemes()) }

    LaunchedEffect (Unit) {
        vm.launch()
    }

    state.forEach {
        if (it.idMeme == currentMeme.id){
            catFavorit = it
        }
    }

    if(catFavorit.id != ""){
        Column(modifier = Modifier.fillMaxSize().padding(100.dp)) {
            Text(text = currentMeme.title)
            Button(
                onClick = {vm.deleteFavorit(catFavorit, controller)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF802A2A)
                )
            ) {
                Text(text = "Удалить из избранного")
            }
        }
    }
    else{
        Column(modifier = Modifier.fillMaxSize().padding(100.dp)) {
            Text(text = currentMeme.title)
            Button(
                onClick = {vm.addFavorit(currentMeme, controller)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "Добавить в избранное")
            }
        }
    }
}

