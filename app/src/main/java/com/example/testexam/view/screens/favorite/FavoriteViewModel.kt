package com.example.testexam.view.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testexam.domain.Constans.currentMeme
import com.example.testexam.domain.Constans.currentUser
import com.example.testexam.domain.Constans.supabase
import com.example.testexam.models.database.FavoriteMemes
import com.example.testexam.models.database.Memes
import com.example.testexam.view.navigation.RoutesNav
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel(){
    var listMemes: MutableList<Memes> = mutableListOf()

    fun launch(){
        viewModelScope.launch {
            var favorites = supabase.from("FavoriteMemes").select() {
                filter {
                    eq("id_user", currentUser)
                }
            }.decodeList<FavoriteMemes>()
            var memes = supabase.from("Memes").select().decodeList<Memes>()
            favorites.forEach { favorite ->
                memes.forEach { meme ->
                    if(meme.id == favorite.idMeme){
                        listMemes.add(meme)
                    }
                }
            }
        }
    }

    fun openItem(value: Memes, controller: NavHostController){
        currentMeme = value
        controller.navigate(RoutesNav.MEME)
    }
}